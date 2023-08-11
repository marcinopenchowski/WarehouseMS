FROM node:17-alpine

WORKDIR /wms

COPY package.json .
COPY package-lock.json .

RUN npm install

COPY . .

RUN npm run build

CMD ["npm", "start"]

