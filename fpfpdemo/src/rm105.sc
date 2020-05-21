;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include game.sh) (include "100.shm")
(use Main)
(use FPRoom)
(use Print)
(use Actor)
(use System)

(public
	rm105 0
)

(local
	titleViewScale =  10
	newPrint
)
(instance rm105 of FPRoom
	(properties
		picture 190
		style FADEOUT
	)
	
	(method (init &tmp i)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theIconBar disable:)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(ego setSpeed: 6)
		(Bclr fDrankBeer)
		(Bclr fGaveMoleToMadame)
		(Bclr fGotMole)
		(Bclr fPutSulfurInCan)
		(Bclr fPutSaltpeterInCan)
		(Bclr fPutCharcoalInCan)
		(Bclr fGaveDocCoffee)
		(Bclr fGotSaltpeter)
		(= ingredientsInCan 0)
		(= i 0)
		(while (< i (inventory size?))
			(if ((inventory at: i) respondsTo: #owner)
				((inventory at: i) owner: 0)
			)
			(++ i)
		)
		(super init:)
		(self setScript: sTownScript)
	)
	
	(method (dispose)
		(super dispose:)
		(theMusic fade: 0 5 5 1)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(event claimed: TRUE)
		)
	)
	
	(method (cue)
		(= timeOver TRUE)
		(curRoom newRoom: 150)
	)
)

(instance sTownScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic
					number: (if (> numVoices 11) 912 else 1912)
					flags: mNOPAUSE
					loop: 1
					play: rm105
				)
				(= seconds 3)
			)
			(1
				(titleView1
					setScale: 10
					setScale:
					setPri: 15
					setLoop: 0
					setStep: 5 5
					init:
					hide:
				)
				(= titleViewScale 10)
				(while (< titleViewScale 129)
					(Animate (cast elements?) TRUE)
					(titleView1 scaleX: titleViewScale scaleY: titleViewScale show:)
					(= titleViewScale (+ titleViewScale 6))
				)
				(= cycles 1)
			)
			(2
				(titleView1 addToPic:)
				(titleView2
					setScale: 10
					setScale:
					setPri: 15
					setLoop: 1
					setStep: 5 5
					init:
					hide:
				)
				(= titleViewScale 10)
				(while (< titleViewScale 129)
					(Animate (cast elements?) TRUE)
					(titleView2 scaleX: titleViewScale scaleY: titleViewScale show:)
					(= titleViewScale (+ titleViewScale 3))
				)
				(= cycles 1)
			)
			(3
				(titleView2 addToPic:)
				(= seconds 3)
			)
			(4
				(= cycles 1)
			)
			(5
				(theGame handsOn:)
				(if (not timeOver)
					(theGame setCursor: ARROW_CURSOR TRUE 62 172)
					(switch
						((= newPrint (Print new:))
							posn: 9 165
							font: 0
							addButton: 1 N_ROOM NULL NULL 1 0 0 100
							addButton: 2 N_ROOM NULL NULL 2 115 0 100
							addButton: 3 N_ROOM NULL NULL 3 230 0 100
							init:
						)
						(1
							(curRoom newRoom: 600)
						)
						(2
							(curRoom newRoom: 150)
							(= timeOver TRUE)
							(theGame setCursor: INVIS_CURSOR)
							(theGame handsOff:)
						)
						(3
							(if (and (theGame quitGame:) (not quit))
								(-- state)
								(= cycles 1)
							)
						)
					)
				else
					(curRoom newRoom: 150)
				)
			)
		)
	)
)

(instance titleView1 of Actor
	(properties
		x 160
		y 55
		view 190
		signal ignrAct
	)
)

(instance titleView2 of Actor
	(properties
		x 160
		y 120
		view 190
		loop 1
		signal ignrAct
	)
)
