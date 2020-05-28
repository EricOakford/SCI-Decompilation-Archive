;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use ForCount)
(use Sound)
(use Motion)
(use System)

(public
	rm030 0
)

(local
	[streetPolyPts 28] = [0 172 22 177 36 174 64 174 80 181 120 173 149 179 168 174 205 180 232 180 256 185 319 184 319 189 0 189]
)
(instance rm030 of SQRoom
	(properties
		picture 30
		style FADEOUT
		east 35
		south 45
		west 25
	)
	
	(method (init)
		(Load SOUND 148)
		(Load SCRIPT FORCOUNT)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 185 293 185 251 187 220 181
						156 182 120 176 81 183 59 176 32 180 0 175
					yourself:
				)
		)
		(switch prevRoomNum
			(east
				(ego y: 186 setHeading: 270)
			)
			(west
				(ego y: 179 setHeading: 90)
			)
			(south 0)
			(else  (ego posn: 160 185))
		)
		(ego init:)
		(lightningScript start: 1)
		(lightning1 init: hide: setScript: lightningScript)
		(thunder init:)
		(theRubble init:)
		(theBackBldgs init:)
		(streetPoly points: @streetPolyPts size: 14)
		(theStreet init:)
		(opening init:)
		(superComputer init:)
		(theArea init:)
		(if (not (Btst fPoliceLanded)) (self setRegions: STREET))
		(super init:)
		(self setRegions: BUNNY)
	)
)

(instance lightningScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
			(1
				(self start: 0)
				(= seconds (Random 3 6))
			)
			(2
				(client show: setCel: 0 setCycle: Forward)
				(= cycles 1)
			)
			(3
				(thunder play:)
				(client setCycle: ForwardCounter (Random 1 3) self)
			)
			(4 (client hide:) (self init:))
		)
	)
)

(instance thunder of Sound
	(properties
		number 150
		priority 15
	)
)

(instance lightning1 of Sq4Prop
	(properties
		x 240
		y 38
		view 32
	)
)

(instance theRubble of Sq4Feature
	(properties
		x 160
		y 5
		nsBottom 200
		nsRight 320
		onMeCheck $2000
		lookStr 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 2))
			(V_SMELL (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theBackBldgs of Sq4Feature
	(properties
		x 160
		y 5
		nsBottom 200
		nsRight 320
		onMeCheck $0040
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 6))
			(V_SMELL (narrator say: 7))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theStreet of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		lookStr 8
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: streetPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance streetPoly of Polygon
	(properties)
)

(instance superComputer of Sq4Feature
	(properties
		x 160
		y 20
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0400
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 12))
			(V_SMELL (narrator say: 13))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance opening of Sq4Feature
	(properties
		x 160
		y 22
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0800
		lookStr 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		lookStr 15
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROPE
				((ScriptID BUNNY 4) doVerb: theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
