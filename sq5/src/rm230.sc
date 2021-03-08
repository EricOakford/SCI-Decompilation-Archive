;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use Talker)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm230 0
	rogTalker 15
)

(instance rm230 of Rm
	(properties
		picture 45
		style $000a
	)
	
	(method (init)
		(curRoom setRegions: 210)
		(Load rsVIEW 236)
		(curRoom setScript: sMeetSpike)
		(spikeFin init:)
		(spikeLeg1 init:)
		(spikeLeg2 init:)
		(rogGrim init:)
		(super init:)
	)
)

(instance theMusic3 of Sound
	(properties)
)

(instance sMeetSpike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic3 number: 248 loop: -1 play: 0 fade:)
			)
			(1
				(rogGrim dispose:)
				(= cycles 1)
			)
			(2 (messager say: 1 0 0 1 self))
			(3 (= seconds 3))
			(4 (messager say: 1 0 0 2 self))
			(5 (= seconds 2))
			(6 (messager say: 1 0 0 3 self))
			(7 (= seconds 1))
			(8
				(theMusic3 fade:)
				(curRoom newRoom: 225)
				(self dispose:)
			)
		)
	)
)

(instance spikeFin of Prop
	(properties
		x 192
		y 61
		view 236
	)
	
	(method (init)
		(self cycleSpeed: 6 setCycle: Fwd)
		(super init: &rest)
	)
)

(instance spikeLeg1 of Prop
	(properties
		x 181
		y 55
		view 236
		loop 1
	)
	
	(method (init)
		(self cycleSpeed: 6 setCycle: Fwd)
		(super init: &rest)
	)
)

(instance spikeLeg2 of Prop
	(properties
		x 215
		y 90
		view 236
		loop 2
	)
	
	(method (init)
		(self cycleSpeed: 6 setCycle: Fwd)
		(super init: &rest)
	)
)

(instance rogGrim of View
	(properties
		x 78
		y 73
		view 236
		loop 3
	)
)

(instance rogTalker of Talker
	(properties
		x 72
		y 64
		view 236
		loop 6
		priority 4
		signal $4011
		talkWidth 110
	)
	
	(method (init)
		(self setPri: 4)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 86
			tailY: 56
			xOffset: 0
			isBottom: 1
		)
		(super init: 0 rogEyes rogerMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance rogerMouth of Prop
	(properties
		nsTop 36
		nsLeft 16
		view 236
		loop 4
		priority 6
		signal $4011
	)
	
	(method (init)
		(self setPri: 6 ignoreActors: 1)
		(super init: &rest)
	)
)

(instance rogEyes of Prop
	(properties
		nsTop 22
		nsLeft 11
		view 236
		loop 5
		cel 1
		signal $4011
	)
	
	(method (init)
		(self setPri: 6)
		(super init: &rest)
	)
)
