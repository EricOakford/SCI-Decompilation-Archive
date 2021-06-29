;;; Sierra Script 1.0 - (do not remove this comment)
(script# TWABOUT)
(include game.sh) (include "884.shm")
(use Main)
(use Print)
(use DCIcon)
(use System)

(public
	aboutScript 0
)

(local
	aboutDone
)
(instance aboutScript of Code

	(method (doit &tmp [str 300] theX theY theView)
		((ScriptID 888 0) hide:)
		(Bset fDemoAbout)
		(UnLoad RES_VIEW 800)
		(UnLoad RES_VIEW 790)
		(UnLoad RES_VIEW 807)
		(Message MsgGet TWABOUT N_TITLE NULL NULL 1 @str)
		(while (not aboutDone)
			(switch
				(Print
					font: userFont
					addTextF: @str version 10 0
					addText: N_TITLE NULL NULL 2 0 22 TWABOUT
					addButton: 1 N_SALES NULL NULL 1 0 36 TWABOUT
					addButton: 2 N_TEAM NULL NULL 1 0 50 TWABOUT
					addButton: 3 N_EXIT NULL NULL 1 0 64 TWABOUT
					init:
				)
				(1
					(if (not (Print addText: N_SHOW_SALES NULL NULL 1 0 0 TWABOUT init:))
						(self dispose:)
						(return)
					)
					(if (not (Print addText: N_SHOW_SALES NULL NULL 2 0 0 TWABOUT init:))
						(self dispose:)
						(return)
					)
					(if (not (Print addText: N_SHOW_SALES NULL NULL 3 0 0 TWABOUT init:))
						(self dispose:)
						(return)
					)
				)
				(2
					(if (or (not aboutQuote) (> (++ aboutQuote) 28))
						(= aboutQuote 13)
					)
					(if (not (Print addText: N_CREDITS NULL C_START_CREDITS 1 0 0 TWABOUT init:))
						(self dispose:)
						(return)
					)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 880)
						(= theX 70)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_SEIBERT 1 theX 0 TWABOUT
								addText: N_CREDITS NULL C_SEIBERT 2 theX 13 TWABOUT
								addText: N_CREDITS NULL C_SEIBERT 3 theX 39 TWABOUT
								addIcon: (musicIcon view: theView cel: 0 loop: 0 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 880)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 880)
						(= theX 50)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_DESIGN 1 theX 0 TWABOUT
								addText: N_CREDITS NULL C_DESIGN 2 theX 13 TWABOUT
								addText: N_CREDITS NULL C_DESIGN 3 theX 39 TWABOUT
								addIcon: (designIcon view: theView cel: 0 loop: 1 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 880)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 889)
						(= theX 0)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_ART 1 0 0 TWABOUT
								addText: N_CREDITS NULL C_ART 2 0 13 TWABOUT
								addText: N_CREDITS NULL C_ART 3 0 26 TWABOUT
								addIcon: (artIcon view: theView cel: 0 loop: 0 yourself:) 0 0 20 65
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 889)
					(if (Btst fDemoAbout)
						(= theView 2000)
					else
						(= theView 886)
					)
					(if
						(not
							(Print
								width: 230
								x: 30
								addText: N_CREDITS NULL C_ART 4 0 0 TWABOUT
								addText: N_CREDITS NULL C_ART 5 0 13 TWABOUT
								addText: N_CREDITS NULL C_ART 6 0 26 TWABOUT
								addIcon: (artIcon view: theView cel: 0 loop: 0 yourself:) 0 0 20 40
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(if (Btst fDemoAbout)
						(= theView 2000)
					else
						(= theView 881)
					)
					(UnLoad RES_VIEW 886)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_ART 7 0 0 TWABOUT
								addText: N_CREDITS NULL C_ART 8 0 13 TWABOUT
								addText: N_CREDITS NULL C_ART 9 0 26 TWABOUT
								addIcon: (artIcon view: theView cel: 0 loop: 0 yourself:) 0 0 30 65
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 881)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 881)
						(= theX 60)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_ART 10 theX 0 TWABOUT
								addText: N_CREDITS NULL C_ART 11 theX 13 TWABOUT
								addText: N_CREDITS NULL C_ART 12 theX 26 TWABOUT
								addIcon: (artIcon view: theView cel: 0 loop: 1 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 881)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 881)
						(= theX 60)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_ART 13 theX 0 TWABOUT
								addText: N_CREDITS NULL C_ART 14 theX 13 TWABOUT
								addText: N_CREDITS NULL C_ART 15 theX 26 TWABOUT
								addIcon: (artIcon view: theView cel: 0 loop: 2 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 881)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 888)
						(= theX 75)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_PROGRAMMERS 1 theX 0 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 2 theX 13 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS aboutQuote theX 26 TWABOUT
								addIcon: (progIcon view: theView cel: 0 loop: 0 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 888)
					(if (Btst fDemoAbout) (= theView 2000) else (= theView 882))
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_PROGRAMMERS 4 0 0 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 5 0 13 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 6 0 39 TWABOUT
								addIcon: (progIcon view: theView cel: 0 loop: 1 yourself:) 0 0 20 65
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 882)
					(if (Btst fDemoAbout)
						(= theView 2000)
						(= theX 0)
					else
						(= theView 882)
						(= theX 50)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_PROGRAMMERS 10 theX 0 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 11 theX 13 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 12 theX 26 TWABOUT
								addIcon: (progIcon view: theView cel: 0 loop: 2 yourself:) 0 0 1 1
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 882)
					(if (Btst fDemoAbout)
						(= theView 2000)
					else
						(= theView 883)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_PROGRAMMERS 7 0 0 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 8 0 13 TWABOUT
								addText: N_CREDITS NULL C_PROGRAMMERS 9 0 26 TWABOUT
								addIcon: (progIcon view: theView cel: 0 loop: 0 yourself:) 0 0 20 70
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 883)
					(if (Btst fDemoAbout)
						(= theView 2000)
					else
						(= theView 890)
						(= theX 85)
					)
					(if
						(not
							(Print
								addText: N_CREDITS NULL C_GRANDSTAFF 1 theX 0 TWABOUT
								addText: N_CREDITS NULL C_GRANDSTAFF 2 theX 13 TWABOUT
								addText: N_CREDITS NULL C_GRANDSTAFF 3 theX 26 TWABOUT
								addIcon: (musicIcon view: theView cel: 0 loop: 0 yourself:) 0 0 0 0
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 890)
					(if (Btst fDemoAbout)
						(= theView 2000)
					else
						(= theView 887)
					)
					(if
						(not
							(Print
								width: 170
								addText: N_CREDITS NULL C_WOOLARD 1 0 0 TWABOUT
								addText: N_CREDITS NULL C_WOOLARD 2 0 13 TWABOUT
								addText: N_CREDITS NULL C_WOOLARD 3 0 39 TWABOUT
								addIcon: (qaIcon view: theView cel: 0 loop: 0 yourself:) 0 0 20 50
								init:
							)
						)
						(self dispose:)
						(return)
					)
					(UnLoad RES_VIEW 887)
				)
				(3
					(= aboutDone TRUE)
					(self dispose:)
					(return)
				)
			)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(DisposeScript TWABOUT)
	)
)

(instance artIcon of DCIcon
	(properties
		cycleSpeed 15
	)
)

(instance designIcon of DCIcon
	(properties
		cycleSpeed 15
	)
)

(instance musicIcon of DCIcon
	(properties
		cycleSpeed 15
	)
)

(instance progIcon of DCIcon
	(properties
		cycleSpeed 15
	)
)

(instance qaIcon of DCIcon
	(properties
		cycleSpeed 15
	)
)
