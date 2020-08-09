;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	GAME.SH--
;***
;**************************************************************

;EO: Some defines have been commented out because they are non-integer defines,
;which SCICompanion does not currently support.

(include pics.sh)
(include views.sh)
(include system.sh) (include sci2.sh) ;system and kernel functions

;
; Colors

;;;(define	darkGrey		grey)
;;;(define	ltWhite		white)

(define	cBlack			%0000000000000001) ;**	Bit mapped controls
(define	cBlue			%0000000000000010)
(define	cGreen			%0000000000000100)
(define	cCyan			%0000000000001000)
(define	cRed			%0000000000010000)
(define	cMagenta		%0000000000100000)
(define	cBrown			%0000000001000000)
(define	cWhite			%0000000010000000)
(define	cLtBlack		%0000000100000000)
(define	cLtBlue			%0000001000000000)
(define	cLtGreen		%0000010000000000)
(define	cLtCyan			%0000100000000000)
(define	cLtRed			%0001000000000000)
(define	cLtMagenta		%0010000000000000)
(define	cLtBrown		%0100000000000000)
(define	cLtWhite		%1000000000000000)

;;;(define	cDarkGray	cLtBlack)			;**	Synonyms
;;;(define	cDarkGrey	cLtBlack)
;;;(define	cGray			cWhite)
;;;(define	cGrey			cWhite)
;;;(define	cPink			cLtRed)
;;;(define	cYellow		cLtBrown)

;
; Global stuff

(define	DEMO			0)
(define	DEMOWIN		1)
(define	DODISP		2)
(define	INNROOM		3)

;
; Actual rooms

(define	TITLE			100)
(define	MAP			110)
(define	BOOGERS		120)
(define	RED_BARON	130)
(define	GOLF			140)
(define	BACKGAMMON	150)
(define	WAITROOM		160)
(define	TRIVIA		170)
(define	BJACK			180)
(define	BOARDS		190)
(define	BRIDGE		200)
(define	CHESS			210)
(define	STRATEGO		220)
(define	TITLE2		230)

;
; Globals

;;;(global
;;;	myTextColor			100
;;;	myBackColor			101
;;;	isVGA					102
;;;	debugging			103
;;;	statusLine			104
;;;	soundFx				105
;;;	theMusic				106
;;;	globalSound			107
;;;)
;;;
;;;(define Cls if modelessDialog (modelessDialog dispose:))
;;;