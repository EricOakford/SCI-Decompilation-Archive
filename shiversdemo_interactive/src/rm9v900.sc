;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9900)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	rm9v900 0
)

(local
	local0
)
(instance rm9v900 of ShiversRoom
	(properties
		picture 9900
	)
	
	(method (init)
		(= local0 0)
		(efExitLeft init: 2)
		(efExitUp init: 4)
		(if
		(and (<= 9901 prevRoomNum) (<= prevRoomNum 9902))
			(sounds play: 10921 0 58 0)
			(pDrawer cel: (pDrawer lastCel:) init:)
			(hsOpenDrawer init:)
			(efBrochure init: 0)
			(= local0 1)
		else
			(pDrawer init:)
			(hsDrawer init:)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(super newRoom: n &rest)
		(if (== local0 1)
			(MonoOut {Door closing sfx:10906})
			(if (!= n 9901) (sounds play: 10906 0 50 0))
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9890
	)
)

(instance efExitUp of ExitFeature
	(properties
		nextRoom 9910
	)
	
	(method (init)
		(self createPoly: 80 119 21 57 21 14 88 1 264 0 264 26)
		(super init: &rest)
	)
)

(instance efBrochure of ExitFeature
	(properties
		nextRoom 9901
	)
	
	(method (init)
		(self createPoly: 151 103 139 96 159 84 191 97 165 115)
		(super init: &rest)
	)
)

(instance hsDrawer of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 122 130 119 102 166 77 166 104)
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local0 1)
		(MonoOut {Opening Drawer})
		(curRoom setScript: sOpenDrawer)
	)
)

(instance hsOpenDrawer of HotSpot
	(properties)
	
	(method (init)
		(self
			createPoly:
				139
				123
				141
				100
				165
				115
				195
				95
				205
				91
				199
				122
				168
				145
				157
				144
				158
				138
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local0 0)
		(MonoOut {Closing Drawer})
		(curRoom setScript: sCloseDrawer)
	)
)

(instance pDrawer of Prop
	(properties
		priority 9
		fixPriority 1
		view 9900
	)
)

(instance sCloseDrawer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDrawer setCycle: Beg init:)
				(sounds play: 10906 0 50 self)
			)
			(1
				(theGame handsOn:)
				(hsDrawer init:)
				(hsOpenDrawer dispose:)
				(efBrochure dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenDrawer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDrawer setCycle: End init:)
				(sounds play: 10905 0 32 self)
			)
			(1
				(theGame handsOn:)
				(hsDrawer dispose:)
				(hsOpenDrawer init:)
				(efBrochure init: 0)
				(self dispose:)
			)
		)
	)
)
