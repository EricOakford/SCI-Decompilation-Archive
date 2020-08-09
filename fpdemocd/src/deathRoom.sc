;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEATH)
(include game.sh) (include "99.shm")
(use Main)
(use FPRoom)
(use Print)
(use DCIcon)
(use Motion)
(use System)

(public
	deathRoom 0
)

(local
	deathSeq
	deathView
	deathLoop
	iconY
	buttonX
	buttonY
	soundNum
)
(instance deathRoom of FPRoom
	(properties
		picture 780
		style FADEOUT
	)
	
	(method (init)
		(theIconBar disable:)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(super init:)
		(self setScript: sDie)
	)
)

(instance sDie of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 fade:)
				(theMusic2 fade:)
				(= cycles 10)
			)
			(1
				(messager say: N_OBITUARY NULL C_FELL 0 self)
			)
			(2
				(= iconY 35)
				(= buttonX 90)
				(= buttonY 60)
				(= deathView 903)
				(= deathLoop 1)
				(theIcon1 view: deathView loop: deathLoop setSize:)
				(deathMusic number: 909 play:)
				(theIcon1 cel: 0)
				(= myBackColor 20)
				(systemWindow color: 0 back: myBackColor)
				(theGame setCursor: ARROW_CURSOR TRUE)
				(repeat
					(switch
						(Print
							font: userFont
							addText: 1 0 0 1 0 0
							addIcon: (theIcon1 yourself:) 0 0 0 iconY
							addButton: 1 N_BUTTON NULL NULL 1 buttonX (- buttonY 20) 99
							addButton: 2 N_BUTTON NULL NULL 2 buttonX buttonY 99
							init:
						)
						(1
							(= myBackColor 34)
							(systemWindow color: 0 back: myBackColor)
							(theGame restart: TRUE)
						)
						(2
							(if (== prevRoomNum 28)
								(if (== deathReason 21)
									(= deathReason 1)
								else
									(++ deathReason)
								)
								(theIcon1 cel: 0)
								(deathMusic fade:)
								(= myBackColor 34)
								(systemWindow color: 0 back: myBackColor)
								(curRoom init:)
							else
								(= quit TRUE)
							)
						)
					)
				)
			)
		)
	)
)

(instance theIcon1 of DCIcon
	(properties
		view 900
		cycleSpeed 15
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)

(instance deathMusic of FPSound
	(properties
		flags mNOPAUSE
	)
)