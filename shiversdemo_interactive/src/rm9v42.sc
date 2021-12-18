;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9420)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v42 0
)

(instance rm9v42 of ShiversRoom
	(properties
		picture 9420
		invView 2
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(if (== global186 1) (efExitForward init:))
		(if (proc951_5 79) (spDoor init:) (vDoorBack init:))
		(if
			(and
				(proc951_5 42)
				(or
					(== prevRoomNum 9850)
					(and (< 9000 prevRoomNum) (< prevRoomNum 9440))
				)
			)
			(MonoOut {fade to 26})
			(sounds fade: 10908 26 15 8 0 0)
		)
		(super init: &rest)
	)
)

(instance psInSlide of PotSpot
	(properties
		nsLeft 151
		nsTop 90
		nsRight 197
		nsBottom 132
	)
)

(instance vDoorBack of View
	(properties
		priority 20
		fixPriority 1
		view 9420
		loop 1
	)
)

(instance spDoor of ShiversProp
	(properties
		priority 30
		fixPriority 1
		view 9420
	)
	
	(method (init)
		(if (== prevRoomNum 936)
			(= cel (self lastCel:))
			(curRoom initRoom: 151 196 90 132)
			(psInSlide init:)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== cel (self lastCel:))
			(self setScript: sDoorClose)
		else
			(self setScript: sDoorOpen)
		)
	)
)

(instance sDoorOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom initRoom: 151 196 90 132)
				(psInSlide init:)
				(sounds stop: 10930)
				(sounds play: 10929 0 82 0)
				(spDoor setCycle: End self)
			)
			(1
				(proc951_3 43)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoorClose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds stop: 10929)
				(sounds play: 10930 0 82 0)
				(spDoor setCycle: Beg self)
			)
			(1
				(psInSlide dispose:)
				(if (!= global106 0) (global346 dispose:))
				(= cycles 1)
			)
			(2
				(proc951_4 43)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9430
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9410
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9410
	)
	
	(method (init)
		(self createPoly: 71 60 185 55 178 132 82 145 71 60)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(user canControl:)
			)
			(event claimed: 1)
			(hsDialogArea init:)
			(vDialog init:)
		)
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

(instance vDialog of View
	(properties
		x 60
		y 110
		priority 250
		fixPriority 1
		view 921
		cel 6
	)
)
