;;; Sierra Script 1.0 - (do not remove this comment)
(script# 39)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)

(public
	rm39 0
	swordMusic 1
)

(instance swordMusic of Sound
	(properties)
)

(instance rightGuard of Prop
	(properties
		y 90
		x 179
		view vCastleGuards
	)
)

(instance leftGuard of Prop
	(properties
		y 90
		x 140
		view vCastleGuards
		loop 1
	)
)

(instance castleDoor of View
	(properties
		y 88
		x 159
		view vCastleGuards
		loop 2
	)
)

(instance rm39 of Room
	(properties
		picture 39
		style WIPEDOWN
		horizon 125
		east 40
		south 37
		west 38
	)
	
	(method (init &tmp theY)
		(Load VIEW vCastleGuards)
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst DEFEATED_WEAPON_MASTER))
				(!= masterDay Day)
			)
			(LoadMany SCRIPT SKILLED 221 222 223 224 220 218 217 216)
			(LoadMany VIEW vWeaponMasterTalk vWeaponMaster vEgoFightWithSword vEgoDefeated vEgoSwordDefeated)
		)
		(SolvePuzzle POINTS_ENTERCASTLECOURTYARD 1)
		(StatusLine enable:)
		(super init: &rest)
		(if (not Night)
			(rightGuard init: stopUpd: addToPic:)
			(leftGuard init: stopUpd: addToPic:)
		)
		(castleDoor init: stopUpd: addToPic:)
		(= theY (ego y?))
		(NormalEgo)
		(ego init:)
		(= yesNoTimer 0)
		(switch prevRoomNum
			(38
				(ego posn: 1 theY setMotion: MoveTo 15 theY)
			)
			(40
				(ego posn: 319 theY setMotion: MoveTo 305 theY)
			)
			(41
				(ego posn: egoX 125 setMotion: MoveTo egoX 135)
			)
			(else 
				(ego posn: 160 189 setMotion: MoveTo 160 170)
			)
		)
		(self setLocales: CASTLE)
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst DEFEATED_WEAPON_MASTER))
				(!= masterDay Day)
			)
			(cond 
				((Btst FLAG_239)
					((ScriptID 221 0) init:)
					(self setScript: (ScriptID 222 1))
				)
				((> (Random 0 100) 50)
					((ScriptID 221 0) setCycle: Walk init:)
					(Bset FLAG_239)
					(self setScript: (ScriptID 222 0))
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== (ego edgeHit?) NORTH)
			(cond 
				((< (ego x?) 100) (ego x: 1))
				((> (ego x?) 203) (ego x: 318))
				(else (ego x: (* (- (ego x?) 97) 3)))
			)
			(curRoom newRoom: 41)
		)
	)
	
	(method (dispose)
		(if (>= Clock 3000) (Bclr FLAG_239))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							(
							(Said '[<at,around][/!*,area,courtyard,street,field]')
							(HighPrint 39 0)
							;You are in the courtyard of Castle Spielburg.
							)
							((Said '/ground,brick,flagstone')
								(HighPrint 39 1)
								;The courtyard is paved with granite paving stones, carefully placed by a skilled craftsman.
								)
							((Said '/man') (if Night
									(HighPrint 39 2)
									;There is nobody else here.
									else
									(HighPrint 39 3)
									;There are two guards near the castle door.
									)
								)
							((Said '/master[<weapon]')
								(HighPrint 39 4)
								;He isn't here at the moment.
								)
							((Said '/east,stable')
								(HighPrint 39 5)
								;What you see, along with what you smell, gives the impression that the building on the east side of the courtyard contains the stables.
								)
							((Said '/west,barrack')
								(HighPrint 39 6)
								;On the west side of the courtyard is a building that looks like soldier's barracks.
								(if (not Night)
									(HighPrint 39 7)
									;As a matter of fact, there's a soldier sleeping in front of it now.
									)
							)
							((Said '/north,castle,door,guard')
								(HighPrint 39 8)
								;The door to the Baron's castle is well guarded.
								)
							((Said '/south,gate,gatehouse,pit')
								(HighPrint 39 9)
								;As you look back at the castle gate, you see a line of bushes along the south wall and the gatehouse and portcullis.
								)
						)
					)
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(if Night
							(HighPrint 39 10)
							;You barely get to sleep when you hear the guards searching the castle grounds for bums.
							;You decide it's time to make like a tree and leave.
							(if (and (< 750 Clock) (< Clock 2550))
								(FixTime 21)
							)
							(curRoom newRoom: 37)
						else
							(HighPrint 39 11)
							;There are too many guards around.
						)
					)
				)
			)
		)
	)
)
