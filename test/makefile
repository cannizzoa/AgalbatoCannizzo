VPATH = tex-parts

ITPD.pdf : ITPD.tex ITPD-p1.tex ITPD-p2.tex ITPD-p3.tex ITPD-p4.tex ITPD-p5.tex ITPD-p6.tex
	pdflatex ITPD.tex
	pdflatex ITPD.tex
	rm -f *.aux
	rm -f *.log
	rm -f *.out
	rm -f *.toc
	rm -f *.synctex.gz
	
.PHONY : install
install : 
	cp ITPD.pdf ../Deliveries/'Agalbato, Cannizzo - Integration Test Plan Document v1.pdf'
