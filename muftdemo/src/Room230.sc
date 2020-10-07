;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh)
(use Main)
(use Procs)
(use TScripts)
(use Intrface)
(use IconBar)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	Room230 0
	door 1
)

(local
	[local0 11]
)
(instance Room230 of FRoom
	(properties
		lookStr {This is the home of Bookwyrm.}
		picture 230
		style HSHUTTER
		north 180
		east 240
		south 280
		west 220
		northX 81
		northY 46
		southX 166
		southY 172
		eastX 316
		eastY 160
		westX 5
		westY 160
	)
	
	(method (init)
		(HandsOff)
		(IconBar disable:)
		(ego normal: TRUE posn: 60 100 init:)
		(if (> walkSound 0)
			(Load SOUND walkSound)
		)
		(super init:)
		(= iconBarActive TRUE)
		(DrawIconScroll)
		(theIconBar enable: show:)
		(theGame setScript: theZapCursor)
		(SetWalkSound)
		(sfx number: 122 play:)
		(if (Btst 5)
			(Bclr 5)
		)
		(birdHouse stopUpd: init:)
		(falls setCycle: Forward init:)
		(door stopUpd: init:)
		(curRoom setScript: birdFly)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (theMusic handle?)
			(theMusic fade: 0 15 12 1)
			(= walkSound 29)
		)
		(super dispose: &rest)
	)
)

(instance birdSound of Sound
	(properties
		flags mNOPAUSE
		number 154
	)
)

(instance birdFly of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 240)
			)
			(1
				(Print 230 0
					#at 70 150
					#color 32
					#time 5
					#dispose
					self
				)
			)
			(2
				(birdSound play:)
				(birdie setCycle: EndLoop loop: 2 cel: 0 setPri: 12 init:)
				(ego setHeading: 95)
				(= ticks 12)
			)
			(3
				(birdie
					setLoop: 3
					setCycle: Forward
					setStep: 6 2
					setMotion: MoveTo -10 157 self
				)
			)
			(4
				(curRoom newRoom: 120)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 129
		y 84
		description {Door}
		lookStr {This is the door to Bookwyrm's house.}
		view 230
	)
)

(instance birdHouse of View
	(properties
		x 283
		y 57
		description {Birdhouse}
		lookStr {The birdhouse is a home for a bird.}
		view 230
		loop 4
		priority 3
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(if (not (birdie script?))
					(birdie setScript: birdFly)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance birdie of Actor
	(properties
		x 283
		y 157
		z 100
		description {Bird}
		lookStr {The bird has a nest in the birdhouse.}
		view 230
		signal (| ignrAct ignrHrz)
	)
)

(instance falls of Prop
	(properties
		x 33
		y 24
		description {Waterfall}
		lookStr {The waterfall flows down to the stream.}
		view 230
		loop 1
	)
)

(instance house of Feature
	(properties
		x 167
		y 64
		description {House}
		sightAngle 90
		onMeCheck cGREEN
		lookStr {Bookwyrm's house has flowers growing on it.}
	)
)

(instance window1 of Feature
	(properties
		x 184
		y 85
		description {Window}
		sightAngle 90
		onMeCheck cCYAN
		lookStr {The windows let you see into the house.}
	)
)

(instance stone of Feature
	(properties
		x 184
		y 140
		description {Stones}
		sightAngle 90
		onMeCheck cRED
		lookStr {The stones make a path to the house.}
	)
)

(instance rock of Feature
	(properties
		x 59
		y 160
		description {Rock}
		sightAngle 90
		onMeCheck cMAGENTA
		lookStr {The rock is dark gray.}
	)
)

(instance road of Feature
	(properties
		x 74
		y 73
		description {Road}
		sightAngle 90
		onMeCheck cBROWN
		lookStr {The road leads north to the crossroads.}
	)
)

(instance fence of Feature
	(properties
		x 71
		y 126
		description {Fence}
		sightAngle 90
		onMeCheck cLGREY
		lookStr {The fence keeps you from falling.}
	)
)

(instance stream of Feature
	(properties
		x 21
		y 94
		description {Stream}
		sightAngle 90
		onMeCheck cGREY
		lookStr {The stream flows to the west.}
	)
)

(instance flower1 of Feature
	(properties
		x 72
		y 171
		description {Flowers}
		sightAngle 90
		onMeCheck cLGREEN
		lookStr {These flowers smell like cherry soda.}
	)
)

(instance flower2 of Feature
	(properties
		x 231
		y 142
		description {Flowers}
		sightAngle 90
		onMeCheck cLCYAN
		lookStr {These flowers smell like orange soda.}
	)
)

(instance flower3 of Feature
	(properties
		x 233
		y 122
		description {Flowers}
		sightAngle 90
		onMeCheck cLRED
		lookStr {These flowers have no smell.}
	)
)

(instance mountain of Feature
	(properties
		x 92
		y 46
		description {Mountain}
		sightAngle 90
		onMeCheck cLMAGENTA
		lookStr {The mountain looks pretty.}
	)
)

(instance cloud of Feature
	(properties
		x 75
		y 35
		description {Clouds}
		sightAngle 90
		onMeCheck cWHITE
		lookStr {The clouds are far above.}
	)
)

(instance grass of Feature
	(properties
		x 143
		y 160
		description {Grass}
		sightAngle 90
		onMeCheck cYELLOW
		lookStr {You can feel the grass under your shoes.}
	)
)

(instance sfx of Sound
	(properties
		flags mNOPAUSE
		number 122
		loop -1
	)
)
