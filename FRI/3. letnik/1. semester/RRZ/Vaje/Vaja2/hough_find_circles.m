function [out_x, out_y, out_r] = hough_find_circles(Ie, radius, varargin)
% Find circles using Hough algorithm
%
% Usage: [rho, theta] = hough_find_circles(edges, radius, ...)
%
% The radius argument can be either a scalar or a vector of multiple radii.
%
% Arguments:
%  - Threshold: selection threshold (number of votes)
%  - Count: number of selected lines (if not set then all above threshold
%  are selected)
%  - Neighborhood: number of neighborhood pixels for local non-maxima
%  suppression (2 by default).
%

threshold = 1;
count = [];
neighborhood = 2;

for j=1:2:length(varargin)
    switch lower(varargin{j})
        case 'threshold', threshold = varargin{j+1};
        case 'count', count = varargin{j+1};
        case 'neighborhood', neighborhood = varargin{j+1};
        otherwise, error(['unrecognized argument ' varargin{j}]);
    end
end

a_size = size(Ie);
M = zeros(a_size(1), a_size(2), numel(radius));

for r = 1:numel(radius)

    A = zeros(prod(a_size), 1);

    cX = round(cos(linspace(0, 2*pi, 180)) * radius(r));
    cY = round(sin(linspace(0, 2*pi, 180)) * radius(r));

    [~, I, ~] = unique(cX + cY * radius(r) * 4);
    cX = cX(I);
    cY = cY(I);

    [X, Y] = find(Ie);

    ind = [X Y];

    for i = ind'
        hx = i(1) + cX;
        hy = i(2) + cY;
        where = hx > 0 & hx <= a_size(1) & hy > 0 & hy <= a_size(2);
        h = sub2ind(a_size, hx(where), hy(where));
        A(h) = A(h) + 1;
    end;

    A = reshape(A, a_size);
    valid = ((ordfilt2(A, (neighborhood * 2 + 1) ^ 2, true(neighborhood * 2 + 1))) == A);
    A(~valid) = 0;
    M(:, :, r) = A;
end;
    
if ~isempty(count) && numel(M) > count
    v = sort(M(:), 'descend');
    threshold = max(threshold, v(count));
end;

[out_y, out_x, out_r] = ind2sub(size(M), find(M(:) >= threshold));
out_r = radius(out_r);
