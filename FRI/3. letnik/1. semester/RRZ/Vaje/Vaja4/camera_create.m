function camera = camera_create(url)
%CAMERA_CREATE creates a camera structure for the given hostname.
%   camera = CAMERA_CREATE(url)
%   
%   url       The hostname for remote camera
%
%   camera    The camera structure
%

url = sprintf('http://%s/api/camera', url);

data = parse_json(urlread(sprintf('%s/describe', url)));

data = data{1};

intrinsics = [data.intrinsics{1}{1}, data.intrinsics{1}{2}, data.intrinsics{1}{3}; ...
    data.intrinsics{2}{1}, data.intrinsics{2}{2}, data.intrinsics{2}{3}; ...
    data.intrinsics{3}{1}, data.intrinsics{3}{2}, data.intrinsics{3}{3}];

distortion = [data.distortion{1}{1}, data.distortion{1}{2}, ...
        data.distortion{1}{3}, data.distortion{1}{4}];

camera = struct('url', url, 'intrinsics', intrinsics, 'distortion', distortion);




