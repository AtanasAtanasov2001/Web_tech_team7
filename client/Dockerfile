# Use a Node 16 base image
FROM node:18-alpine as builder

# Set the working directory to /app inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install
RUN npm ci

# Copy the rest of the app files
COPY . .

# Build the app
RUN npm run build

# Use a lightweight Node image for the production stage
FROM node:18-alpine

# Set the working directory to /app inside the container
WORKDIR /app

# Copy the built assets from the builder stage
COPY --from=builder /app/build ./build

# Install serve for serving the static files
RUN npm install -g serve

# Expose the desired port (e.g., 3000)
EXPOSE 3000

# Start the app with the serve command
CMD ["serve", "-s", "build"]