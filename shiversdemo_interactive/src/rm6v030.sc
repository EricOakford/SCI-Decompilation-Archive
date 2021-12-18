;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6030)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm6v030 0
)

(local
	local0
	local1
)
(instance rm6v030 of ShiversRoom
	(properties
		picture 6030
	)
	
	(method (init)
		(= local1 1)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsTapestry init:)
		(hsDoor init:)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 5)
		(cond 
			((and (>= 30000 n) (>= n 9000))
				(sounds fadeChain:)
				(if (not local1) (sounds play: 10620 0 40 0))
			)
			((== n -27426)
				(if (proc951_11 2 6000)
					(sounds fade: 20603 0 5 16 1 0)
					(proc951_9 20601)
					(sounds play: 20601 -1 0 0)
					(sounds fade: 20601 10 1 30 0 0)
					(sounds playChain: -1 -4 20602 -1)
					(sounds adjChainVol: 34)
				)
			)
			(else
				(if local0 (proc951_3 43))
				(if (not local1)
					(curRoom drawPic: 6030)
					(sounds play: 10620 0 40 0)
				)
			)
		)
		(super newRoom: n &rest)
	)
)

(instance hsTapestry of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 20 70 117 70 117 145 20 145)
		(super init: &rest)
	)
	
	(method (doVerb)
		(proc951_16 145)
		(= local1 0)
		(sounds play: 10610 0 32 0)
		(curRoom drawPic: 6031)
		(if (not global186)
			(efExitSecret init: 3)
		else
			(hsSecretPassage init:)
		)
		(hsTapestryDrop init:)
		(self dispose:)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6060
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6040
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9020
	)
	
	(method (init)
		(self createPoly: 137 142 137 42 197 42 197 143)
		(super init: &rest)
	)
)

(instance efExitSecret of ExitFeature
	(properties
		nextRoom -27426
	)
	
	(method (init)
		(self createPoly: 37 104 113 104 112 137 37 139)
		(super init: &rest)
	)
)

(instance pDoor of Prop
	(properties
		view 6030
		cycleSpeed 18
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 137 142 137 42 197 42 197 143)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sDoorOpens)
	)
)

(instance hsTapestryDrop of HotSpot
	(properties)
	
	(method (init)
		(self
			createPoly:
				21
				0
				22
				142
				35
				101
				118
				101
				118
				141
				133
				141
				134
				40
				203
				39
				203
				142
				243
				142
				243
				0
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local1 1)
		(sounds play: 10620 0 32 0)
		(curRoom drawPic: 6030)
		(hsTapestry init:)
		(efExitSecret dispose:)
		(self dispose:)
	)
)

(instance sDoorOpens of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDoor init: setPri: 25 1)
				(proc951_7 10613)
				(= cycles 1)
			)
			(1
				(pDoor setCycle: End)
				(sounds play: 10613 0 40 self)
			)
			(2
				(if (not local1)
					(= local1 1)
					(sounds play: 10620 0 32 self)
					(curRoom drawPic: 6030)
					(hsTapestry init:)
					(hsTapestryDrop dispose:)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(efExitForward init: 3)
				(= local0 1)
				(hsDoor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance hsSecretPassage of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 37 104 113 104 112 137 37 139)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== global186 1) (curRoom setScript: sPurchaseMe))
	)
)

(instance hsDialogArea of HotSpot
	(properties
		nsRight 266
		nsBottom 143
	)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler delete: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb)
		(vDialog dispose:)
		(self dispose:)
	)
)

(instance sPurchaseMe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(vDialog init:)
				(sounds play: -30516 0 106 self)
			)
			(1
				(hsDialogArea init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance vDialog of View
	(properties
		x 58
		y 125
		priority 250
		fixPriority 1
		view 921
		cel 6
	)
)
