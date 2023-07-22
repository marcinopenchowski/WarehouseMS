FROM node:17-alpine

ENV NODE_ENV development

WORKDIR /wms

COPY package.json .
COPY package-lock.json .

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "start"]