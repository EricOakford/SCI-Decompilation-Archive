;;; Sierra Script 1.0 - (do not remove this comment)
(script# CLOUDS)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rgClouds 0
	StartCloudRoom 1
)

(procedure (StartCloudRoom egoPosn param2 param3 param4 param5 param6 param7 param8 param9 &tmp oldRoomDir)
	(= oldRoomDir prevRoomNum)
	(if (OneOf oldRoomDir 0 -1 777)
		(= oldRoomDir (curRoom param9?))
	)
	(switch oldRoomDir
		((curRoom north?)
			(ego posn: [egoPosn 0] [egoPosn 1])
			(curRoom style: WIPEDOWN)
		)
		((curRoom south?)
			(ego posn: [egoPosn 2] [egoPosn 3])
			(curRoom style: WIPEUP)
		)
		((curRoom east?)
			(ego posn: [egoPosn 4] [egoPosn 5])
			(curRoom style: WIPELEFT)
		)
		((curRoom west?)
			(ego posn: [egoPosn 6] [egoPosn 7])
			(curRoom style: WIPERIGHT)
		)
	)
)

(class cloudRoom of Room
	(properties)
	
	(method (init)
		(LoadMany VIEW 4 7 8 34 63 67)
		(LoadMany SOUND 95 17)
		(if (ego has: iMagicRing)
			(LoadMany VIEW 38 39)
		)
		(if (ego has: iDagger)
			(Load VIEW 53)
		)
		(Bset fLittleEgo)
		(NormalEgo)
		(super init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (dispose)
		(Bclr fLittleEgo)
		(super dispose:)
	)
)

(instance rgClouds of Region
	(properties)
	
	(method (init)
		(cond 
			(
				(or
					(== curRoomNum 56)
					(== curRoomNum 72)
					(== curRoomNum 82)
				)
				(if (!= ((ScriptID 0 23) number?) 4)
					(Load SOUND 4)
					((ScriptID 0 23) number: 4 loop: -1 play:)
				)
			)
			(
				(or
					(== prevRoomNum 69)
					(== prevRoomNum 58)
					(!= ((ScriptID 0 23) number?) 54)
					(== ((ScriptID 0 23) prevSignal?) -1)
				)
				(Load SOUND 54)
				((ScriptID 0 23) number: 54 loop: -1 play:)
			)
		)
		(LoadMany PICTURE 80 81 27)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(or
					(& (ego onControl:) cMAGENTA)
					(and
						(& (ego onControl:) cBLUE)
						(not stalkRoom)
					)
				)
				(== newRoomNum curRoomNum)
			)
			(curRoom setScript: egoFallThruClouds)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((super handleEvent: event)
					(return TRUE)
				)
				((Said 'get,take/boulder,pebble,pebble')
					(Print 610 0)
				)
				((Said 'look,check>')
					(cond 
						((Said '[<at,around][/around,room,cloud]')
							(if (== curRoomNum 58)
								(Print 610 1)
							else
								(Print 610 2)
							)
						)
						((Said '/ceder')
							(Print 610 3)
						)
					)
				)
			)
		)
	)
)

(instance egoFallThruClouds of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 23) fade:)
				((ScriptID 0 21) number: 17 loop: 1 play:)
				(if (Btst fInvisible)
					(Print 610 4)
				)
				(ego view: 63 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 80 WIPEDOWN)
				(if (ego blocks?)
					((ego blocks?) dispose:)
					(ego blocks: 0)
				)
				(ego
					view: 8
					setLoop: 5
					cel: 0
					setCycle: Forward
					cycleSpeed: 1
					ignoreActors:
					illegalBits: 0
					setStep: 4 6
					setPri: 14
					y: -5
					setMotion: MoveTo (ego x?) 220 self
					show:
				)
			)
			(2
				(curRoom drawPic: 81 WIPEDOWN)
				(ego
					y: -5
					setStep: 4 8
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(3
				(curRoom drawPic: 27 WIPEDOWN)
				(ego
					view: 34
					setCycle: 0
					setLoop: 4
					setPri: 12
					posn: 210 -5
					setStep: 2 15
					setMotion: MoveTo 210 151 self
				)
			)
			(4
				(crater init:)
				((ScriptID 0 21) number: 95 play:)
				(self cue:)
			)
			(5
				(ego loop: 2 cel: 0 cycleSpeed: 1 posn: 210 161)
				(RedrawCast)
				(ShakeScreen 6)
				(ego setCycle: EndLoop self)
			)
			(6
				(HandsOn)
				(EgoDead 610 5)
				(self dispose:)
			)
		)
	)
)

(instance crater of View
	(properties
		x 210
		y 161
		description {hole}
		view 34
	)
)
