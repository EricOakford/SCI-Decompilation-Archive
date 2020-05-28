;;; Sierra Script 1.0 - (do not remove this comment)
(script# 525)
(include game.sh)
(use Main)
(use brain)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use System)

(public
	rm525 0
)

(instance rm525 of SQRoom
	(properties
		picture 525
		style $000a
		north 510
		south 541
		west 520
		vanishingX -200
		vanishingY -280
	)
	
	(method (init)
		(switch prevRoomNum
			(north
				(self setScript: enterScript 0 (brain level?))
			)
			(541
				(self setScript: enterScript 0 0)
			)
			(else  (HandsOn))
		)
		(ego init:)
		(self setRegions: BRAIN)
		(super init:)
		(switch (brain level?)
			(2
				(brain
					makePolygon: 0 132 57 132 91 189 0 189
					makePolygon: -80 38 46 112 -80 112
					makePolygon: 0 0 319 0 319 189 135 189
				)
			)
			(3
				(brain
					makePolygon: 0 0 102 0 117 24 0 24
					makePolygon: 120 0 319 0 319 82 227 22 143 22
					makePolygon: -50 37 227 37 390 164 208 164 128 79 -50 76
					makePolygon: 0 97 124 97 194 183 319 183 319 189 0 189
				)
			)
		)
		((ScriptID BRAIN 4)
			x: 6
			y: 106
			nsLeft: 2
			nsTop: 103
			nsBottom: 109
			nsRight: 11
			sightAngle: 90
			init:
		)
		((ScriptID BRAIN 5)
			x: 70
			y: 63
			nsLeft: 63
			nsTop: 58
			nsBottom: 69
			nsRight: 77
			sightAngle: 90
			init:
		)
		(roomFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(if (== (brain level?) 3)
			(if (ego inRect: 0 0 64 40)
				(ego setPri: 4)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			(script)
			((IsObjectOnControl ego cGREEN) (self setScript: oSS 0 4))
			((IsObjectOnControl ego cRED) (self setScript: oSS 0 16))
			(
			(and (IsObjectOnControl ego cMAGENTA) (== (brain level?) 2)) (self setScript: exitScript))
			((IsObjectOnControl ego cCYAN)
				(if (== (brain level?) 3)
					(brain exitDir: 315)
				else
					(brain exitDir: 150)
				)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath -8 -1 self)
			)
			(1
				(curRoom newRoom: 510)
				(self dispose:)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2] egoX egoY newX newY)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch register
					(0
						(= egoX 150)
						(= egoY 240)
						(= newX 113)
						(= newY 174)
					)
					(3
						(= egoX 95)
						(= egoY -8)
						(= newX 134)
						(= newY 24)
					)
					(else 
						(= egoX -8)
						(= egoY -1)
						(= newX 32)
						(= newY 48)
					)
				)
				(ego
					x: egoX
					y: egoY
					setMotion: PolyPath newX newY self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance oSS of Script
	(properties)
	
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(if (ego mover?)
					(= theX ((ego mover?) x?))
					(= theY ((ego mover?) y?))
				else
					(= theX (= theY 0))
				)
				(HandsOff)
				(if (or (> theX 319) (== theX 0))
					(if (== register 16)
						(ego setMotion: PolyPath 294 173 self)
					else
						(ego setMotion: PolyPath 308 87 self)
					)
				else
					(ego setMotion: PolyPath theX theY self)
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance roomFeature of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 500 say: 2)
			)
			(V_TALK
				(narrator modNum: 500 say: 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
