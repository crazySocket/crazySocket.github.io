derivation {
  system = "x86_64-linux";
  name = "hello-1.0";
  builder = ./hello.sh;
  source = ./hello-1.0.tar.gz;
}
