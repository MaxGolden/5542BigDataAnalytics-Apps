# coding=utf-8
from PIL import Image
import glob, os

in_dir = 'tmp_photo'  # 源图片目录
out_dir = in_dir + '_out'  # 转换后图片目录
percent = 0.4  # 缩放比例
if not os.path.exists(out_dir): os.mkdir(out_dir)

# 图片批处理
def main():
    for files in glob.glob(in_dir + '/*'):
        filepath, filename = os.path.split(files)
        im = Image.open(files).convert('L')
        # w, h = im.size
        # im = im.resize((int(w * percent), int(h * percent)))
        im.save(os.path.join(out_dir, filename))


if __name__ == '__main__':
    main()

