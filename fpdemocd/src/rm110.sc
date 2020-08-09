;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh) (include "0.shm")
(use Main)
(use FPRoom)
(use Actor)
(use System)

(public
	rm110 0
)

(local
	titleScale
)
(instance rm110 of FPRoom
	(properties
		picture 190
		style FADEOUT
	)
	
	(method (init)
		(theMusic1
			number: 104
			flags: mNOPAUSE
			loop: 1
			prevSignal: 0
			play:
		)
		(super init:)
		(titleView1 init: hide:)
		(titleView2 init: hide:)
		(self setScript: sTownScript)
	)
)

(instance sTownScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable:)
				(= cycles 1)
			)
			(1
				(if (== (theMusic1 prevSignal?) 10)
					(= cycles 1)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(2
				(yellSound number: 2918 flags: mNOPAUSE loop: 1 play:)
				(titleView1
					setScale: 10
					setScale:
					setPri: 15
					setLoop: 0
					setStep: 5 5
				)
				(= titleScale 10)
				(while (< titleScale 129)
					(Animate (cast elements?) TRUE)
					(titleView1 scaleX: titleScale scaleY: titleScale show:)
					(+= titleScale 6)
				)
				(= cycles 1)
			)
			(3
				(if (>= (theMusic1 prevSignal?) 20)
					(= cycles 1)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(4
				(titleView1 stopUpd:)
				(titleView2
					setScale: 10
					setScale:
					setPri: 15
					setLoop: 1
					setStep: 5 5
				)
				(= titleScale 10)
				(while (< titleScale 129)
					(Animate (cast elements?) TRUE)
					(titleView2 scaleX: titleScale scaleY: titleScale show:)
					(+= titleScale 3)
				)
				(= cycles 1)
			)
			(5
				(titleView2 stopUpd:)
				(theMusic1 fade: self)
			)
			(6
				(cast eachElementDo: #dispose)
				(curRoom style: FADEOUT drawPic: 780)
				(= cycles 5)
			)
			(7
				(messager say: N_HELLO NULL NULL 0 self 0)
			)
			(8 (= ticks 60))
			(9
				(curRoom newRoom: 230)
				(self dispose:)
			)
		)
	)
)

(instance titleView1 of Actor
	(properties
		x 160
		y 60
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

(instance yellSound of FPSound
	(properties
		flags mNOPAUSE
	)
)
