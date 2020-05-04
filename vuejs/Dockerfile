FROM nginx
COPY default.conf kc.conf /etc/nginx/conf.d/

COPY index.html favicon.ico  /usr/share/nginx/html/
EXPOSE 8080 80
CMD ["nginx", "-g", "daemon off;"]
