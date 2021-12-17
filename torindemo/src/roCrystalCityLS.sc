;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16000)
(include sci.sh)
(use Main)
(use TorinEgo)
(use TPRoom)
(use Script)
(use ExitFeature)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)

(public
	roCrystalCityLS 0
)

(local
	local0
)
(instance soWowCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(messager say: 0 0 1)
				(self dispose:)
			)
		)
	)
)

(instance foForestExit of ExitFeature
	(properties
		approachX 290
		approachY 276
		x 290
		y 276
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 290 298 389 272 450 317 293 319
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(curRoom newRoom: 10100)
	)
)

(instance foCliffsExit of ExitFeature
	(properties
		approachX 290
		approachY 276
		x 290
		y 276
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 245 163 627 164 630 269 353 252 239 278 112 315 0 318 0 146
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(curRoom setScript: soExitToCity)
	)
)

(instance soExitToCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: soTorinExitToCity self)
				((ScriptID 64018 0) setScript: soBoogleExitToCity self)
			)
			(1)
			(2
				(curRoom newRoom: 16100)
				(self dispose:)
			)
		)
	)
)

(instance soTorinExitToCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego oPlane: foCliffsExit self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(ego setLoop: setPri: 5 setMotion: MoveTo 178 470 self)
			)
			(3
				(ego
					setLoop: 3
					nCurPosX: 24
					setPri: 5
					posn: 142 308
					setMotion: MoveTo 135 270 self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance soBoogleExitToCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< ((ScriptID 64018 0) x?) 279)
					(= local0 1)
					((ScriptID 64018 0)
						bSwing: 0
						setMotion: PolyPath 215 289 self
					)
				else
					((ScriptID 64018 0)
						bSwing: 0
						setMotion: PolyPath 365 259 self
					)
				)
			)
			(1
				(if local0
					((ScriptID 64018 0) setHeading: 0 self)
				else
					((ScriptID 64018 0) setHeading: 315 self)
				)
			)
			(2
				(if local0
					((ScriptID 64018 0)
						bSwing: 0
						setPri: 5
						setLoop:
						setMotion: MoveTo 178 360 self
					)
				else
					((ScriptID 64018 0)
						bSwing: 0
						setPri: 5
						setLoop:
						setMotion: MoveTo 178 360 self
					)
				)
			)
			(3
				((ScriptID 64018 0)
					setLoop: 3
					nCurPosX: 24
					setPri: 5
					posn: 148 299
					setMotion: MoveTo 148 279 self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance soEnterFromCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: soTorinEnterFromCity self)
				((ScriptID 64018 0)
					setScript: soBoogleEnterFromCity self
				)
			)
			(1)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTorinEnterFromCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init: oPanner: posn: 258 365)
				(self
					setScript:
						((WalkBehindHill new:)
							lastVerb: ego
							setNoScore: 287
							unSet: 273
							priority: 5
							heading: 135
							lastNoun: self
							yourself:
						)
				)
			)
			(1
				(ego oPanner: setPri: -1 setMotion: MoveTo 301 278 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance soBoogleEnterFromCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					init:
					oPanner:
					bSwing: 0
					posn: 334 316
				)
				(self
					setScript:
						((WalkBehindHill new:)
							lastVerb: (ScriptID 64018 0)
							setNoScore: 359
							unSet: 256
							priority: 5
							heading: 135
							lastNoun: self
							yourself:
						)
				)
			)
			(1
				((ScriptID 64018 0)
					setPri: -1
					setMotion: MoveTo 383 270 self
				)
			)
			(2
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance coStartMusic of CueObj
	(properties)
	
	(method (cue)
		(music1 pageSize: 16000)
	)
)

(instance roCrystalCityLS of TPRoom
	(properties
		picture 16000
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOff:)
		(ego init: oPanner:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 338 266 275 286 205 294 123 321 574 317 569 275 494 267
					yourself:
				)
		)
		(music1 lThumbLoop: 16099 coStartMusic)
		(foForestExit init:)
		(foCliffsExit init:)
		(switch prevRoomNum
			(16100
				(curRoom setScript: soEnterFromCity)
			)
			(10100
				(ego
					loop: 3
					posn: 464 384
					setMotion: MoveTo 365 313 (ScriptID 64020 0)
				)
				((ScriptID 64018 0) posn: 493 301 loop: 3 init: oPanner:)
				(if (not ((ScriptID 64017 0) test: 23))
					(curRoom setScript: soWowCity)
					((ScriptID 64017 0) set: 23)
				)
			)
			(else 
				(theGame handsOn:)
				(ego posn: 296 277 loop: 4)
				((ScriptID 64018 0) posn: 252 292 loop: 4 init: oPanner:)
			)
		)
	)
)
