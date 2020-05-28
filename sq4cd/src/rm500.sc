;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh)
(use Main)
(use brain)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Motion)
(use System)

(public
	rm500 0
)

(instance rm500 of SQRoom
	(properties
		picture 500
		style FADEOUT
		east 505
		south 515
		vanishingX 380
		vanishingY -75
	)
	
	(method (init)
		(Load VIEW 508)
		(if (== prevRoomNum 515)
			(self setScript: enterScript)
		else
			(HandsOn)
		)
		(ego init:)
		(self setRegions: BRAIN)
		(super init:)
		(brain
			makePolygon: 0 189 0 0 319 0 319 108 165 104 59 189
		)
		(brain makePolygon: 110 189 174 125 319 125 319 189)
		((ScriptID BRAIN 5)
			x: 237
			y: 90
			nsLeft: 230
			nsTop: 85
			nsBottom: 96
			nsRight: 244
			sightAngle: 90
			init:
		)
		(light init: setCycle: Forward)
		(roomFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(if (IsObjectOnControl ego 8) (brain exitDir: 225))
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 52 y: 229 setMotion: PolyPath 98 183 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance light of Sq4Prop
	(properties
		x 110
		y 49
		view 508
		lookStr 1
	)
)

(instance roomFeature of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
