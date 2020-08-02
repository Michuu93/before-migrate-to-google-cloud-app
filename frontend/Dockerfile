### Build ###
FROM node:14.7.0-alpine3.12 AS BUILDER
WORKDIR /usr/src/app
COPY package.json yarn.lock ./
RUN yarn
COPY . ./
RUN yarn build

### Run  ###
FROM nginx:1.19.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=BUILDER /usr/src/app/dist/frontend-app /usr/share/nginx/html
EXPOSE 80
