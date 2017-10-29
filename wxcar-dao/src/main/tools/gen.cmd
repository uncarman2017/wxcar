if exist d:\wxcar (echo "已经存在d:\wxcar") else (md d:\wxcar)

java -jar mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite