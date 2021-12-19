;;; Sierra Script 1.0 - (do not remove this comment)
(script# 900)
(include sci.sh)
(use Main)
(use Plane)
(use Motion)
(use Actor)
(use System)

(public
	sierraLogo 0
)

(local
	local0
	local1
)
(instance sierraLogo of ShiversRoom
	(properties)
	
	(method (init)
		(= local1
			((Plane new:)
				picture: 900
				priority: 40
				init: 0 0 320 200
				yourself:
			)
		)
		(super init:)
		(star0 init: z: 1000 cueWho: star1)
		(star1 init: z: 1000 cueWho: star2)
		(star2 init: z: 1000 cueWho: star3)
		(star3 init: z: 1000 cueWho: star4)
		(star4 init: z: 1000 cueWho: star5)
		(star5 init: z: 1000 cueWho: star6)
		(star6 init: z: 1000 cueWho: star7)
		(star7 init: z: 1000 cueWho: star8)
		(star8 init: z: 1000 cueWho: star9)
		(star9 init: z: 1000 cueWho: star10)
		(star10 init: z: 1000 cueWho: star11)
		(star11 init: z: 1000 cueWho: star12)
		(star12 init: z: 1000 cueWho: star13)
		(star13 init: z: 1000)
		(star14 init: z: 1000)
		(star15 init: z: 1000)
		(mouseDownHandler add: curRoom)
		(keyDownHandler add: curRoom)
		(directionHandler add: curRoom)
		(self setScript: sShowLogo)
	)
	
	(method (dispose)
		(sounds stop: 607)
		(local1 dispose:)
		(mouseDownHandler delete: curRoom)
		(keyDownHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb)
		(sounds stop: 607)
		(theGame handsOn:)
		(curRoom newRoom: 910)
	)
)

(instance logo of View
	(properties
		view 900
	)
)

(class Star of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 1
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		cueWho 0
	)
	
	(method (init)
		(self cel: 0 setCycle: 0)
		(super init: &rest)
	)
	
	(method (cue)
		(if cueWho (cueWho z: 0 setCycle: End cueWho))
		(self dispose:)
	)
)

(instance star0 of Star
	(properties
		x 232
		y 57
		view 900
	)
)

(instance star1 of Star
	(properties
		x 166
		y 78
		view 900
	)
)

(instance star2 of Star
	(properties
		x 174
		y 89
		view 900
	)
)

(instance star3 of Star
	(properties
		x 183
		y 100
		view 900
	)
)

(instance star4 of Star
	(properties
		x 237
		y 68
		view 900
	)
)

(instance star5 of Star
	(properties
		x 113
		y 78
		view 900
	)
)

(instance star6 of Star
	(properties
		x 162
		y 69
		view 900
	)
)

(instance star7 of Star
	(properties
		x 159
		y 58
		view 900
	)
)

(instance star8 of Star
	(properties
		x 141
		y 38
		view 900
	)
)

(instance star9 of Star
	(properties
		x 151
		y 47
		view 900
	)
)

(instance star10 of Star
	(properties
		x 187
		y 120
		view 900
	)
)

(instance star11 of Star
	(properties
		x 186
		y 111
		view 900
	)
)

(instance star12 of Star
	(properties
		x 99
		y 98
		view 900
	)
)

(instance star13 of Star
	(properties
		x 94
		y 109
		view 900
	)
)

(instance star14 of Star
	(properties
		x 162
		y 69
		view 900
	)
)

(instance star15 of Star
	(properties
		x 151
		y 47
		view 900
	)
)

(instance sShowLogo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theGame handsOff:)
				(logo init:)
				(= seconds 1)
			)
			(2
				(sounds play: 607 0 127 0)
				(= ticks 100)
			)
			(3
				(star0 z: 0 setCycle: End star1)
				(= seconds 13)
			)
			(4
				(star14 z: 0 setCycle: End)
				(= ticks 75)
			)
			(5
				(star15 z: 0 setCycle: End)
				(= seconds 5)
			)
			(6
				(sounds stop: 607)
				(= seconds 2)
			)
			(7
				(theGame handsOn:)
				(curRoom newRoom: 910)
			)
		)
	)
)
