;;; Sierra Script 1.0 - (do not remove this comment)
(script# 803)
(include game.sh)
(use Main)
(use InterProp)
(use Sync)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm803 0
)

(local
	local0
	local1 = [0 0 83 144 239 145]
)
(instance rm803 of Room
	(properties
		picture 111
		style FADEOUT
	)
	
	(method (init &tmp [temp0 10])
		(super init:)
		(user mapKeyToDir: FALSE)
		(cHead init:)
		(sHead init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(characters init:)
		(nSign init:)
		(ySign init:)
		(nHead init: setCycle: Forward)
		(yHead init: setCycle: Forward)
		(curRoom setScript: charlieScript)
	)
	
	(method (dispose)
		(user mapKeyToDir: TRUE)
		(DisposeScript SYNC)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(theSound dispose:)
		(mySound dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(switch (event type?)
				(keyDown
					(event claimed: TRUE)
					(switch (event message?)
						(DOWNARROW
							(if (> (++ local0) 2)
								(= local0 1)
							)
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(TAB
							(if (> (++ local0) 2)
								(= local0 1)
							)
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(UPARROW
							(if (< (-- local0) 1)
								(= local0 2)
							)
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(SHIFTTAB
							(if (< (-- local0) 1)
								(= local0 2)
							)
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(LEFTARROW
							(if (< (-- local0) 1) (= local0 2))
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(RIGHTARROW
							(if (> (++ local0) 2) (= local0 1))
							(theGame
								setCursor: ARROW_CURSOR TRUE [local1 (* local0 2)] [local1 (+ (* local0 2) 1)]
							)
						)
						(else 
							(event claimed: FALSE)
							(super handleEvent: event)
						)
					)
				)
				(else 
					(super handleEvent: event)
				)
			)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance goBackScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(nSign setCel: 1)
				(= cycles 1)
			)
			(1
				(mySound number: 910 setLoop: 1 play:)
				(= ticks 6)
			)
			(2
				(nSign setCel: 0)
				(= ticks 6)
			)
			(3
				(mySound number: 1146 setLoop: 1 play: self)
				(if (DoSound NumDACs)
					(cHead setPri: 15 setCycle: CycleTo 2 1)
					(sHead setPri: 15 setCycle: CycleTo 2 1)
				)
			)
			(4
				(if (DoSound NumDACs)
					(cHead setCycle: 0 setCel: 0)
					(sHead setCycle: 0 setCel: 0)
				)
				(= cycles 2)
			)
			(5
				(theGame restart:)
			)
		)
	)
)

(instance charlieScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(cHead
					setLoop: 0
					setCel: 0
					setPri: 15
					setCycle: MouthSync 803 0 0 0 2
				)
				(= cycles 1)
			)
			(2
				(DoAudio Play 803 0 0 0 2)
				(= ticks 54)
			)
			(3
				(cHead setCycle: 0 setCel: 0)
				(= cycles 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sayOkScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ySign setCel: 1)
				(= cycles 1)
			)
			(1
				(mySound number: 910 setLoop: 1 play:)
				(= ticks 6)
			)
			(2
				(ySign setCel: 0)
				(= ticks 6)
			)
			(3
				(theSound number: 1147 loop: 1 play: self)
				(if (DoSound NumDACs)
					(cHead setPri: 15 setCycle: CycleTo 2 1)
					(sHead setPri: 15 setCycle: CycleTo 2 1)
				)
			)
			(4
				(if (DoSound NumDACs)
					(cHead setCycle: 0 setCel: 0)
					(sHead setCycle: 0 setCel: 0)
				)
				(= cycles 2)
			)
			(5
				(theGame handsOff:)
				(theGame quitGame: 1)
			)
		)
	)
)

(instance characters of View
	(properties
		x 160
		y 89
		view 1
		signal (| stopUpdOn fixPriOn)
	)
)

(instance cHead of Prop
	(properties
		x 144
		y 57
		view 992
	)
)

(instance sHead of Prop
	(properties
		x 179
		y 46
		view 991
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance nSign of InterProp
	(properties
		x 80
		y 159
		view 1
		loop 6
		priority 2
		signal (| stopUpdOn fixPriOn)
	)
	
	(method (doVerb)
		(theGame handsOff:)
		(curRoom setScript: goBackScript)
	)
)

(instance ySign of InterProp
	(properties
		x 239
		y 161
		view 1
		loop 7
		priority 2
		signal (| stopUpdOn fixPriOn)
	)
	
	(method (doVerb)
		(curRoom setScript: sayOkScript)
	)
)

(instance nHead of Prop
	(properties
		x 79
		y 133
		view 1
		loop 2
		priority 3
		signal (| stopUpdOn fixPriOn)
	)
)

(instance yHead of Prop
	(properties
		x 241
		y 135
		view 1
		loop 1
		priority 3
		signal (| stopUpdOn fixPriOn)
	)
)

(instance theSound of Sound)

(instance mySound of Sound)
