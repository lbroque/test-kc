FROM nginx
COPY default.conf /etc/nginx/conf.d/
COPY index.html favicon.ico  /usr/share/nginx/html/
CMD ["nginx", "-g", "daemon off;"]
