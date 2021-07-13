;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgWater)
(include game.sh)
(use Main)
(use Intrface)
(use n802)
(use RegionPath)
(use QScript)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Water 0
	EgoWaterViewer 1
	bikini3Lady 2
	bikini4Lady 3
)

(local
	local0
	pathPts = [
		NODIRECTION 7 273 117 104 132 48 235
		NODIRECTION 5 72 104 135 104 325 184
		NODIRECTION 4 76 95 128 103 325 178
		NODIRECTION 3 -5 90 325 100
		NODIRECTION 2 -5 100 180 160 325 185
		NODIRECTION 12 -5 170 175 156 325 128
		NODIRECTION 13 -5 128 325 106
		NODIRECTION 24 -5 116 184 111 155 72 -5 130
		NODIRECTION 13 325 106 -5 128
		NODIRECTION 12 325 128 175 156 -5 170
		NODIRECTION 2 325 150 -5 100
		NODIRECTION 3 325 100 -5 90
		NODIRECTION 4 325 178 128 103 76 95
		NODIRECTION 5 325 184 135 104 72 104
		NODIRECTION 7 48 235 104 132 273 117
		PATHEND
		]
)
(procedure (GiveDate &tmp thisDate temp1)
	(= temp1 (& (= thisDate (GetTime SYSDATE)) $001f))
	(Printf 301 22
		(& (>> thisDate $0005) $000f)
		(+ (if (< temp1 29) metAgentStacy else 0) thisDate)
		(- (>> thisDate $0009) 60)
	)
)

(instance EgoWaterViewer of Code
	
	(method (doit &tmp egoView)
		(if (== (= egoView (ego view?)) 206)
			(ego observeControl: cLRED cLCYAN)
			(if (& (proc802_0 ego) $1000)
				(if (not local0)
					(Print 301 0)
					(= local0 1)
				)
			else
				(= local0 0)
			)
		else
			(ego
				ignoreControl: cLRED cLCYAN
				view:
					(switch (ego onControl: origin)
						(cLGREEN 216)
						(cLCYAN 215)
						(cLRED 214)
						(else 
							(if (OneOf egoView 214 215) 200 else egoView)
						)
					)
			)
			(if (and (!= egoView (ego view?)) (ego mover?))
				((ego mover?) init:)
			)
		)
	)
	
	(method (dispose)
		(ego viewer: 0)
		(super dispose:)
	)
)

(instance Water of Region
	
	(method (init)
		(Load VIEW 217)
		(Load VIEW 216)
		(Load VIEW 215)
		(Load VIEW 214)
		(Load VIEW 702)
		(Load VIEW 202)
		(Load VIEW 902)
		(super init: &rest)
		(ego viewer: EgoWaterViewer)
		(closeUpView init:)
		(if (not script)
			(self setScript: beachWalkerScript)
		)
		(if (IsObject (script register?))
			((script register?) init: setMotion: beachWalkerPath)
		)
		(if (not (theGame script?))
			(theGame setScript: musicScript)
		)
	)
	
	(method (dispose)
		(EgoWaterViewer dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'swim')
				(if (== (ego view?) 206)
					(Print 301 1)
				else
					(Print 301 2)
				)
			)
			((or (Said 'climb') (Said 'get/sand,plant,water'))
				(DontNeedTo)
			)
			((Said 'walk,enter,go<in,in/bush')
				(Print 301 3)
			)
			((Said 'look>')
				(cond 
					((Said '/palm,bush,plant,vegetation')
						(Print 301 4)
					)
					((Said '/water,bay,lagoon')
						(Print 301 5)
					)
					((Said '<up')
						(Print 301 6)
					)
					((Said '[<at,down,in][/beach,sand,floor]')
						(if (OneOf (ego view?) 317 216 215)
							(Print 301 7)
						else
							(switch (Random 1 3)
								(1 (Print 301 8))
								(2 (Print 301 9))
								(3 (Print 301 10))
							)
						)
					)
					((Said '[<at,up][/sky]')
						(switch (Random 1 2)
							(1 (Print 301 11))
							(2 (Print 301 12))
						)
					)
				)
			)
			((Said 'kiss,suck,fuck,touch/babe,cunt,boob')
				(Print 301 13)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(not
				(= keep
					(OneOf newRoomNumber 1 2 3 4 5 7 12 13 9 24 8 16)
				)
			)
			(theGame setScript: 0)
		)
		(= initialized FALSE)
		(super newRoom: newRoomNumber &rest)
	)
	
	(method (notify param1 obj &tmp i)
		(switch param1
			(0
				(= i 0)
				(while (< i (- argc 2))
					((Clone obj)
						x: [obj (++ i)]
						y: [obj (++ i)]
						loop: [obj (++ i)]
						init:
					)
				)
			)
			(1
				(beachWalkerScript register?)
				(return)
			)
		)
	)
)

(instance musicScript of Script

	(method (doit)
		(if (and (== state 0) (== (globalSound prevSignal?) -1))
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(globalSound stop:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 5 loop: 2 play:)
			)
			(1
				(globalSound number: 4 loop: -1 play:)
			)
		)
	)
)

(instance closeUpView of View
	(properties
		y 85
		x 265
		loop 4
	)
	
	(method (init)
		(super init:)
		(self hide: setPri: 15)
	)
	
	(method (show theView)
		(self view: theView setLoop: 4)
		(super show:)
	)
)

(instance bikini3Lady of Actor
	(properties
		view 702
	)
	
	(method (init)
		(super init:)
		(self
			setAvoider: Avoider 1
			ignoreHorizon:
			setCycle: Walk
			observeControl: cLCYAN
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((IsOffScreen self))
			(
				(Said
					'look[<at][/babe,brunette,native[<native,brunette]]'
				)
				(closeUpView show: view)
				(Animate (cast elements?) FALSE)
				(Print 301 14)
				(closeUpView hide:)
				(event claimed: TRUE)
			)
			((Said 'whistle')
				(Print 301 15)
			)
			((Said 'ask<out/babe')
				(Print 301 16)
			)
			((Said '[/bitch,cunt,boob,brunette,babe[<native]]>')
				(cond 
					((Said 'ask//date')
						(GiveDate)
					)
					((Said 'ask//time')
						(Print 301 17)
					)
					((Said 'chase')
						(Print 301 18)
					)
					((Said 'address')
						(Print 301 19)
					)
					((Said 'kiss,suck,fuck,touch')
						(if (> (ego distanceTo: self) 50)
							(Print 301 20)
						else
							(QueScript self 352)
						)
					)
				)
			)
		)
	)
)

(instance bikini4Lady of Actor
	(properties
		view 202
	)
	
	(method (init)
		(super init:)
		(self
			setAvoider: Avoider 1
			ignoreHorizon:
			setCycle: Walk
			observeControl: cLCYAN
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((IsOffScreen self))
			((Said 'look[<at][/blond,babe[<blond]]')
				(closeUpView show: view)
				(Animate (cast elements?) FALSE)
				(Print 301 21)
				(closeUpView hide:)
				(event claimed: TRUE)
			)
			((Said 'whistle')
				(Print 301 15)
			)
			((Said 'ask<out/babe')
				(Print 301 16)
			)
			((Said '[/bitch,cunt,boob,blond,babe[<blond]]>')
				(cond 
					((Said 'ask//date')
						(GiveDate)
					)
					((Said 'ask//time')
						(Print 301 17)
					)
					((Said 'chase')
						(Print 301 18)
					)
					((Said 'address')
						(Print 301 19)
					)
					((Said 'kiss,suck,fuck,touch')
						(if (> (ego distanceTo: self) 50)
							(Print 301 20)
						else
							(QueScript self 353)
						)
					)
				)
			)
		)
	)
)

(instance beachWalkerPath of RegionPath
	(properties
		value 29
		endType 0
		theRegion 301
	)
	
	(method (nextRoom)
		(if
			(and
				(== (self at: (+ 2 value)) 3)
				(== curRoomNum 3)
				(curRoom notify: 0)
			)
			(repeat
				(-= value 2)
				(breakif (== (self at: (+ 1 value)) NODIRECTION))
			)
		)
		(super nextRoom: &rest)
	)
	
	(method (at n)
		(return [pathPts n])
	)
)

(instance beachWalkerScript of Script
	
	(method (changeState newState &tmp theRegister)
		(switch (= state newState)
			(0
				(= theRegister
					(switch (Random 2 3)
						(2 bikini3Lady)
						(3 bikini4Lady)
					)
				)
				(beachWalkerPath value: -1)
				(if (IsObject register)
					(register dispose:)
				)
				(if (== theRegister register)
					(self init:)
				else
					((= register theRegister)
						init:
						setMotion: beachWalkerPath self
					)
				)
			)
			(1
				(self init:)
			)
		)
	)
)
