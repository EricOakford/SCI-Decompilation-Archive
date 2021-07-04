;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Print)
(use System)

(public
	rm30 0
)

(instance rm30 of KQRoom
	(properties
		picture 99
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(super init:)
		(self setScript: doTheOpening)
	)
)

(instance doTheOpening of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(if (== demoScripts 1)
					(RemapColors RemapByRange 254 90)
					(theMusic number: 51 setLoop: -1 play:)
				)
				(SetFontRes 320 200)
				(Print
					x: -1
					y: -1
					fore: (if (== demoScripts 1) 53 else 33)
					back: 0
					skip: 250
					addText: {Roberta Williams} -1 10
					font: bigFont
					addText: {Kings Quest VII} -1 40
					font: smallFont
					addText: {The Princeless Bride} -1 70
					ticks: 360
					init:
				)
				(SetFontRes 640 480)
				(= seconds 2)
			)
			(2
				(if (== demoScripts 1)
					(++ demoScripts)
					(theGame setCursor: normalCursor TRUE 150 100)
					(if
						(Print
							font: smallFont
							addText: {Include scrolling?}
							addButton: 1 {<Yes>} 0 10
							addButton: 0 {<No>} 0 24
							init:
						)
						(= scrollingIsOn TRUE)
					else
						(= scrollingIsOn FALSE)
					)
				)
				(Bset 21)
				(SetCursor 0)
				(if scrollingIsOn
					(curRoom newRoom: 1400)
				else
					(curRoom newRoom: 1250)
				)
			)
		)
	)
)
