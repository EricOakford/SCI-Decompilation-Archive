;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include sci.sh)
(use Main)
(use AnimatePrint)
(use KQ6Room)
(use CartoonScript)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	rm370 0
)

(instance myConv of Conversation
	(properties)
)

(instance rm370 of KQ6Room
	(properties
		picture 370
		horizon 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
		(theIconBar
			enable:
			disable: 0 1 2 3 4 5 6
			height: -100
			activateHeight: -100
		)
		(Cursor showCursor: 0)
		(theMusic number: 370 setLoop: -1 play:)
		(egoLegs addToPic:)
		(lHand init: stopUpd:)
		(rHand init: stopUpd:)
		(myHead init:)
		(ego
			view: 374
			normal: 0
			loop: 0
			cel: 0
			posn: 155 147
			init:
		)
		(kingarm init: stopUpd:)
		(queenHand init:)
		(candle1 init: setCycle: Forward)
		(candle2 init: setCycle: Forward)
		(cond 
			((and (Btst 1) (not (Btst 3)))
				(if (== msgType 2)
					(curRoom setScript: savedCelesteCD)
				else
					(curRoom setScript: savedCelesteTXT)
				)
			)
			((== msgType 2) (curRoom setScript: caughtAtGateCD))
			(else (curRoom setScript: caughtAtGateTXT))
		)
	)
	
	(method (dispose)
		(theIconBar height: 0 activateHeight: 0)
		(Cursor showCursor: 1)
		(ego setScale: 0)
		(theIconBar enable: 6)
		(theGame setCursor: waitCursor)
		(keyDownHandler delete: self)
		(DisposeScript 371)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(& (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
			)
			(event message: 114)
		)
		(return 0)
	)
)

(instance AlexPrint of AnimatePrint
	(properties)
	
	(method (init)
		(= myMouth alexHead)
		(= x -1)
		(= y 140)
		(super init:)
	)
)

(instance AzurePrint of AnimatePrint
	(properties)
	
	(method (init)
		(= myMouth azureMouth)
		(= myEyes azureEyes)
		(= x 10)
		(= y 110)
		(super init:)
	)
)

(instance AerielPrint of AnimatePrint
	(properties)
	
	(method (init)
		(= myMouth aerielMouth)
		(= myEyes aerielEyes)
		(= x 70)
		(= y 110)
		(super init:)
	)
)

(instance mouthScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: RandCycle)
				(if (< (= seconds (+ register 1)) 1) (= seconds 1))
			)
			(1 (client dispose:))
		)
	)
)

(instance alexHead of Prop
	(properties
		x 142
		y 50
		view 374
		loop 4
		priority 15
		signal $6010
		cycleSpeed 8
	)
	
	(method (init param1)
		(myHead hide:)
		(super init:)
		(self setScript: mouthScr 0 param1)
	)
	
	(method (dispose)
		(myHead show:)
		(super dispose:)
	)
)

(instance eyeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(client show: setCycle: EndLoop self)
			)
			(2 (= cycles 2))
			(3 (client setCycle: BegLoop self))
			(4 (= cycles 2))
			(5
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance azureMouth of Prop
	(properties
		x 115
		y 41
		view 370
		loop 4
		priority 15
		signal $6010
	)
	
	(method (init param1)
		(super init:)
		(self setScript: mouthScr 0 param1)
	)
)

(instance azureEyes of Prop
	(properties
		x 108
		y 36
		view 370
		loop 6
		priority 15
		signal $6010
	)
	
	(method (init)
		(super init:)
		(self hide: setScript: eyeScr)
	)
)

(instance aerielMouth of Prop
	(properties
		x 207
		y 45
		view 370
		loop 5
		priority 15
		signal $6010
	)
	
	(method (init param1)
		(super init:)
		(self setScript: mouthScr 0 param1)
	)
)

(instance aerielEyes of Prop
	(properties
		x 201
		y 42
		view 370
		loop 7
		priority 15
		signal $6010
	)
	
	(method (init)
		(super init:)
		(self hide: setScript: eyeScr)
	)
)

(instance flyer of Actor
	(properties
		view 353
		signal $6000
	)
)

(instance caughtAtGateCD of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(myConv
					add: -1 1 0 1 1
					add: -1 1 0 1 2
					add: -1 1 0 1 3
					add: -1 1 0 1 4
					add: -1 1 0 1 5
					add: -1 1 0 1 6
					add: -1 1 0 1 7
					add: -1 1 0 1 8
					add: -1 1 0 1 9
					add: -1 1 0 1 10
					init: self
				)
			)
			(2
				(Bset 2)
				(cond 
					(
						(and
							(ego has: 2)
							(ego has: 18)
							(ego has: 48)
							(ego has: 41)
						)
						(ego setScript: toLabyrinth)
					)
					((== msgType 2) (ego setScript: toBeachCD))
					(else (ego setScript: toBeachTXT))
				)
			)
		)
	)
)

(instance caughtAtGateTXT of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(kingarm setLoop: 2 setCycle: EndLoop)
				(AzurePrint addText: 1 0 1 1 init:)
			)
			(2
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(3
				(AlexPrint addText: 1 0 1 2 init:)
			)
			(4 (= cycles 2))
			(5
				(AerielPrint addText: 1 0 1 3 init:)
			)
			(6 (= cycles 2))
			(7
				(AzurePrint addText: 1 0 1 4 init:)
			)
			(8 (= cycles 2))
			(9
				(kingarm setLoop: 1 setCycle: EndLoop)
				(AzurePrint addText: 1 0 1 5 init:)
			)
			(10
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(11
				(queenHand cel: 1)
				(AerielPrint addText: 1 0 1 6 init:)
			)
			(12
				(queenHand cel: 0)
				(= cycles 2)
			)
			(13
				(AerielPrint addText: 1 0 1 7 init:)
			)
			(14 (= cycles 2))
			(15
				(kingarm setLoop: 2 setCycle: EndLoop)
				(AzurePrint addText: 1 0 1 8 init:)
			)
			(16
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(17
				(AlexPrint addText: 1 0 1 9 init:)
			)
			(18 (= cycles 2))
			(19
				(kingarm setLoop: 2 setCycle: EndLoop)
				(AzurePrint addText: 1 0 1 10 init:)
			)
			(20
				(kingarm setCycle: BegLoop)
				(Bset 2)
				(cond 
					(
						(and
							(ego has: 2)
							(ego has: 18)
							(ego has: 48)
							(ego has: 41)
						)
						(curRoom setScript: toLabyrinth)
					)
					((== msgType 2) (curRoom setScript: toBeachCD))
					(else (curRoom setScript: toBeachTXT))
				)
			)
		)
	)
)

(instance toLabyrinth of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (== msgType 2)
					(messager say: 1 0 2 1 self)
				else
					(AlexPrint addText: 1 0 2 1 init:)
				)
			)
			(2 (= cycles 2))
			(3
				(if (== msgType 2)
					(messager say: 1 0 2 2 self)
				else
					(kingarm setLoop: 2 setCycle: EndLoop)
					(AzurePrint addText: 1 0 2 2 init:)
				)
			)
			(4
				(ego hide:)
				(egoLegs dispose:)
				(lHand dispose:)
				(rHand dispose:)
				(myHead dispose:)
				(kingarm dispose:)
				(queenHand dispose:)
				(candle1 dispose:)
				(candle2 dispose:)
				(curRoom setScript: flyToCliff)
			)
		)
	)
)

(instance toBeachCD of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myConv
					add: -1 1 0 3 1
					add: -1 1 0 3 2
					add: -1 1 0 3 3
					add: -1 1 0 3 4
					add: -1 1 0 3 5
					add: -1 1 0 3 6
					add: -1 1 0 3 7
					init: self
				)
			)
			(1
				(ego hide:)
				(egoLegs dispose:)
				(lHand dispose:)
				(rHand dispose:)
				(myHead dispose:)
				(kingarm dispose:)
				(queenHand dispose:)
				(candle1 dispose:)
				(candle2 dispose:)
				(curRoom setScript: flyToBeach)
			)
		)
	)
)

(instance toBeachTXT of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(queenHand cel: 2)
				(AerielPrint addText: 1 0 3 1 init:)
			)
			(1
				(queenHand cel: 0)
				(= cycles 2)
			)
			(2
				(kingarm setLoop: 2 setCycle: EndLoop)
				(AzurePrint addText: 1 0 3 2 init:)
			)
			(3
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(4
				(kingarm setLoop: 1 setCycle: EndLoop)
				(AzurePrint addText: 1 0 3 3 init:)
			)
			(5
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(6
				(AlexPrint addText: 1 0 3 4 init:)
			)
			(7 (= cycles 2))
			(8
				(queenHand cel: 2)
				(AerielPrint addText: 1 0 3 5 init:)
			)
			(9 (= cycles 2))
			(10
				(queenHand cel: 0)
				(AerielPrint addText: 1 0 3 6 init:)
			)
			(11 (= cycles 2))
			(12
				(AlexPrint addText: 1 0 3 7 init:)
			)
			(13
				(ego hide:)
				(egoLegs dispose:)
				(lHand dispose:)
				(rHand dispose:)
				(myHead dispose:)
				(kingarm dispose:)
				(queenHand dispose:)
				(candle1 dispose:)
				(candle2 dispose:)
				(curRoom setScript: flyToBeach)
			)
		)
	)
)

(instance savedCelesteCD of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(myConv
					add: -1 1 0 4 1
					add: -1 1 0 4 2
					add: -1 1 0 4 3
					add: -1 1 0 4 4
					add: -1 1 0 4 5
					add: -1 1 0 4 6
					add: -1 1 0 4 7
					init: self
				)
			)
			(2
				(curRoom setScript: flyToOracle)
			)
		)
	)
)

(instance savedCelesteTXT of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(kingarm setLoop: 1 setCycle: EndLoop)
				(AzurePrint addText: 1 0 4 1 init:)
			)
			(2
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(3
				(AzurePrint addText: 1 0 4 2 init:)
			)
			(4 (= cycles 2))
			(5
				(AzurePrint addText: 1 0 4 3 init:)
			)
			(6 (= cycles 2))
			(7
				(kingarm setLoop: 1 setCycle: EndLoop)
				(AzurePrint addText: 1 0 4 4 init:)
			)
			(8
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(9
				(AzurePrint addText: 1 0 4 5 init:)
			)
			(10 (= cycles 2))
			(11
				(kingarm setLoop: 1 setCycle: EndLoop)
				(AzurePrint addText: 1 0 4 6 init:)
			)
			(12
				(kingarm setCycle: BegLoop)
				(= cycles 2)
			)
			(13
				(AlexPrint addText: 1 0 4 7 init:)
			)
			(14
				(curRoom setScript: flyToOracle)
			)
		)
	)
)

(instance flyToOracle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(egoLegs dispose:)
				(lHand dispose:)
				(rHand dispose:)
				(myHead dispose:)
				(kingarm dispose:)
				(queenHand dispose:)
				(candle1 dispose:)
				(candle2 dispose:)
				(curRoom drawPic: 350 10)
				(= seconds 3)
			)
			(1
				(flyer
					posn: 139 11
					setLoop: 1
					setScale: Scaler 50 49 190 0
					init:
					setCycle: Forward
					setMotion: MoveTo 174 14 self
				)
			)
			(2
				(flyer dispose:)
				(= cycles 2)
			)
			(3
				(curRoom drawPic: 98)
				(= seconds 3)
			)
			(4
				(theMusic fade: 0 15 15)
				(curRoom newRoom: 380)
			)
		)
	)
)

(instance flyToCliff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 350 10)
				(flyer
					posn: 139 11
					setLoop: 1
					setScale: Scaler 50 49 190 0
					init:
					setCycle: Forward
					setMotion: MoveTo 280 -15 self
				)
			)
			(1
				(theMusic fade: 0 15 15)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance flyToBeach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 350 10)
				(flyer
					posn: 139 11
					setLoop: 1
					setScale: Scaler 50 49 190 0
					init:
					setCycle: Forward
					setMotion: MoveTo 280 -15 self
				)
			)
			(1
				(theMusic fade: 0 15 15)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance egoLegs of View
	(properties
		x 152
		y 189
		view 374
		loop 1
		priority 14
		signal $4010
	)
)

(instance lHand of View
	(properties
		x 123
		y 137
		view 374
		loop 2
		priority 13
		signal $4010
	)
)

(instance rHand of View
	(properties
		x 183
		y 138
		view 374
		loop 3
		priority 13
		signal $4010
	)
)

(instance myHead of View
	(properties
		x 145
		y 50
		view 374
		loop 5
		cel 1
		priority 15
		signal $4010
	)
)

(instance candle1 of Prop
	(properties
		x 34
		y 68
		view 371
		cel 1
		signal $4000
	)
)

(instance candle2 of Prop
	(properties
		x 285
		y 69
		view 371
		loop 1
		cel 2
		signal $4000
	)
)

(instance kingarm of Actor
	(properties
		x 103
		y 56
		view 370
		signal $4000
		cycleSpeed 20
	)
)

(instance queenHand of Actor
	(properties
		x 173
		y 78
		view 370
		loop 3
		signal $4000
	)
)
