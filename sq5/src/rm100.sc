;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh) (include "0.shm") (include "100.shm")
(use Main)
(use Intrface)
(use Print)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	[local0 2]
)
(instance myDialog of Dialog

	(method (handleEvent)
		(Palette PALCycle 96 218 -1)
		(super handleEvent: &rest)
	)
)

(instance rm100 of Room
	(properties
		picture 300
	)
	
	(method (init)
		(Palette PALLoad 999 2)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(theIconBar hide: disable:)
		(user canInput: TRUE)
		(self setScript: rmScript)
	)
	
	(method (dispose)
		(theIconBar hide: enable:)
		(= normalCursor ARROW_CURSOR)
		(Palette PALLoad 999 2)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if (and (!= (rmScript state?) 4) (& (event type?) (| userEvent mouseDown keyDown)))
			(rmScript changeState: 4)
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event)
		)
	)
)

(instance rmScript of Script
	
	(method (doit)
		(Palette PALCycle 96 218 -1)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp printRet [str 10])
		(switch (= state newState)
			(0
				(theMusic1 number: 1001 loop: 1 play:)
				(sparkle init:)
				(= seconds 4)
			)
			(1
				(if (== (theMusic1 prevSignal?) 20)
					(sparkle setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(2
				(if (== (theMusic1 prevSignal?) 30)
					(sparkle x: 60 y: 145 loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3 (= seconds 3))
			(4
				(theMusic1 stop:)
				(sparkle hide:)
				(= cycles 5)
			)
			(5
				(sparkle dispose:)
				(= seconds 0)
				(= normalCursor ARROW_CURSOR)
				(theGame setCursor: ARROW_CURSOR TRUE)
				(Print
					dialog: myDialog
					font: userFont
					width: 150
					mode: teJustCenter
					addText: N_START V_LOOK NULL 4 0 0 0
					addText: N_START V_LOOK NULL 5 0 10 0
					addColorButton: 0 N_START V_LOOK NULL 6 0 30 0 0 15 23 5 5 5
					addColorButton: 1 N_START V_LOOK NULL 1 0 40 0 0 15 23 5 5 5
					addColorButton: 2 N_START V_LOOK NULL 2 0 50 0 0 15 23 5 5 5
				)
				(switch
					(= printRet
						(Print
							addColorButton: 4 N_START V_LOOK NULL 7 0 60 0 0 15 23 5 5 5
							addColorButton: 3 N_START V_LOOK NULL 3 0 70 0 0 15 23 5 5 5
							init:
						)
					)
					(0 (curRoom newRoom: 104))
					(1 (curRoom newRoom: 110))
					(2
						(theGame restore:)
						(self changeState: state)
					)
					(3
						(= quit TRUE)
					)
					(4
						(= state (- state 2))
						(messager say: N_TECHNOBABBLE NULL NULL 0 self 100)
					)
					(5
						(= str 0)
						(= printRet
							(Print
								addText: {Which room do you want?}
								addText: {Other} 110 25
								addEdit: @str 6 115 35
								posn: 50 30
								addButton: 100 {Start 100} 5 35
								init:
							)
						)
						(if str
							(= printRet (ReadNumber @str))
							(curRoom newRoom: printRet)
						else
							(self changeState: state)
						)
					)
				)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		x 121
		y 54
		view 3001
		priority 15
		signal fixPriOn
	)
)
