function [out_rho, out_theta] = hough_find_lines(Ie, varargin)
% Find lines using Hough algorithm
%
% Usage: [rho, theta] = hough_find_lines(edges, ...)
%
% Arguments:
%  - Threshold: selection threshold (number of votes)
%  - Count: number of selected lines (if not set then all above threshold
%  are selected)
%  - RhoBins: number of bins for rho
%  - ThetaBins: number of bins for theta
%  - Neighborhood: number of neighborhood pixels for local non-maxima
%  suppression (2 by default).
%

threshold = 1;
count = [];
bins_rho = [];
bins_theta = 180;
neighborhood = 2;


for j=1:2:length(varargin)
    switch lower(varargin{j})
        case 'threshold', threshold = varargin{j+1};
        case 'rhobins', bins_rho = varargin{j+1};
        case 'thetabins', bins_theta = varargin{j+1};
        case 'count', count = varargin{j+1};
        case 'neighborhood', neighborhood = varargin{j+1};
        otherwise, error(['unrecognized argument ' varargin{j}]);
    end
end

val_theta = (linspace(-90, 90, bins_theta) / 180) * pi;
max_rho = round(sqrt(sum(size(Ie).^2)));

if isempty(bins_rho), bins_rho = max_rho; end;

val_rho = linspace(-max_rho, max_rho, bins_rho);

a_size = [length(val_rho), length(val_theta)];
A = zeros(prod(a_size), 1);

[X, Y] = find(Ie);

ind = [X, Y];
for i = ind'
    r = round(i(1) * cos(val_theta) + i(2) * sin(val_theta));
    r = round(((r + max_rho) / (2*max_rho)) * length(val_rho));
    where = r > 0 & r <= a_size(1);
    h = sub2ind(a_size, r(where), find(where));
    A(h) = A(h) + 1;
end;

A = reshape(A, a_size);

valid = ((ordfilt2(A, (neighborhood * 2 + 1) ^ 2, true(neighborhood * 2 + 1))) == A);
A(~valid) = 0;

if ~isempty(count) && numel(A) > count
    v = sort(A(:), 'descend');
    threshold = max(threshold, v(count));
end;

[out_rho, out_theta] = find(A >= threshold);
out_rho = val_rho(out_rho);
out_theta = val_theta(out_theta);
