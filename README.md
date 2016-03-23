# qa_sentiment
Sentiment polarity detection

Command-line parameters:
<ul>
<li>-F features - feature to evaluate. A for All, S for Semantic, L for Lexicon, K for Keyword</li>
<li>-i filename - the dataset to use (.csv)</li>
<li>-W filename - the word space to use (.json)</li>
<li>-oc filename - output csv </li>
<li>-oa filename - output arff</li>
</ul>

Example command-line:
<pre>
java -jar qa_sentiment.jar -F A -i input.csv -W wordSpace.json -oc output.csv -oa output.arff
</pre>

It would need more memory. You can set it with parameter <pre>-Xmx</pre>  

Example command-line:
<pre>
java -jar -Xmx4096m qa_sentiment.jar -F A -i input.csv -W wordSpace.json -oc output.csv -oa output.arff
</pre>
