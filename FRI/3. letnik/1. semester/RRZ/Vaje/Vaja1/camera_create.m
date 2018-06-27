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

intrinsics = [data.intrinsics.m00, data.intrinsics.m01, data.intrinsics.m02; ...
    data.intrinsics.m10, data.intrinsics.m11, data.intrinsics.m12; ...
    data.intrinsics.m20, data.intrinsics.m21, data.intrinsics.m22];

distortion = [data.distortion.m0, data.distortion.m1, ...
        data.distortion.m2, data.distortion.m3];

camera = struct('url', url, 'intrinsics', intrinsics, 'distortion', distortion);




