VPATH = tex-parts

PP.pdf : PP.tex PP-p1.tex PP-p2.tex PP-p3.tex PP-p4.tex PP-p5.tex
	pdflatex PP.tex
	pdflatex PP.tex
	rm -f *.aux
	rm -f *.log
	rm -f *.out
	rm -f *.toc
	rm -f *.synctex.gz
	
.PHONY : install
install : 
	cp PP.pdf ../Deliveries/'Agalbato, Cannizzo - Project Plan v1.pdf'
