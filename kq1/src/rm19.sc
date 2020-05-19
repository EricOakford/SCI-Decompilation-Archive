;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use ForCount)
(use LoadMany)
(use Follow)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm19 0
)

(local
	[aSparkle 20]
)
(instance rm19 of Room
	(properties
		picture 19
		north 30
		east 18
		south 14
		west 20
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(LoadMany VIEW 219 1 45)
		(LoadMany SOUND 56 29)
		(super init:)
		(door init: stopUpd:)
		(Bclr fMountainDoorOpen)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 90 (ego x?) 30) 57 init:)
				(NormalEgo)
			)
			(south
				(ego y: 188 init:)
				(NormalEgo)
			)
			(west
				(ego x: 3 init:)
				(NormalEgo)
			)
			(east
				(ego posn: 317 (proc0_17 189 (ego y?) 120) init:)
				(NormalEgo)
			)
			(else 
				(ego posn: 215 120 init:)
				(door cel: 8 setScript: doorCloses)
				(self setScript: outOfMount)
			)
		)
		(smallTree init:)
		(bush1 init:)
		(bush2 init:)
		(hill1 init:)
		(hill2 init:)
		(hill3 init:)
		(hill4 init:)
		(hill5 init:)
		(rock init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(and
					(> (ego y?) 119)
					(> (ego x?) 63)
					(< (theGoat y?) 120)
					(not (theGoat script?))
					(cast contains: theGoat)
				)
				(theGoat setScript: catchUp)
			)
			(
				(and
					(& (theGoat onControl: origin) cBLUE)
					(Btst fGoatFollows)
					(!= (theGoat priority?) 2)
				)
				(theGoat setPri: 2)
			)
			(
				(and
					(not (& (theGoat onControl: origin) cBLUE))
					(Btst fGoatFollows)
					(== (theGoat priority?) 2)
				)
				(theGoat setPri: -1)
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
			(
				(and
					(& (ego onControl: origin) cBLUE)
					(!= (ego priority?) 2)
					(!= (ego script?) climbHill)
				)
				(ego setPri: 2)
			)
			(
				(and
					(not (& (ego onControl: origin) cBLUE))
					(== (ego priority?) 2)
					(!= (ego script?) climbHill)
				)
				(ego setPri: -1)
			)
			((and (& (ego onControl: origin) cLBLUE) (!= (ego script?) climbHill))
				(ego setScript: climbHill)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<around,at][/room,hill]')
						(Print 19 0)
					)
					((Said '/keyhole')
						(if (ego has: iKey)
							(Print 19 1)
						else
							(Print 19 2)
						)
					)
					((Said 'ceder')
						(Print 19 3)
					)
				)
			)
			((Said 'climb,scale/hill')
				(Print 19 4)
			)
			((Said 'pick/lock')
				(cond 
					((and (ego has: iKey) (Print 19 5)) (Print 19 6))
					((ego inRect: 188 105 236 128)
						(Print 19 7)
					)
					(else
						(Print 19 8)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'bend/door,hinge,lock')
				(if (ego inRect: 188 105 236 128)
					(ego loop: 3)
					(RedrawCast)
					(Print 19 9)
				else
					(Print 19 10)
				)
			)
			((Said 'knock[/door]')
				(if (ego inRect: 188 105 236 128)
					(ego loop: 3)
					(RedrawCast)
					(curRoom setScript: knockDoor)
				else
					(Print 19 10)
				)
			)
			((Said 'open,open/door')
				(cond 
					((not (ego inRect: 188 105 236 128))
						(Print 19 11)
					)
					((Btst fMountainDoorOpen)
						(Print 19 12)
					)
					((ego has: iKey)
						(ego loop: 3)
						(RedrawCast)
						(Print 19 13)
						(SolvePuzzle fUnlockedMountainDoor 2)
						(ego setScript: doorOpens)
					)
					(else (Print 19 14))
				)
			)
			(
				(and
					(not (ego inRect: 188 105 236 128))
					(or
						(Said 'unlock/door')
						(Said 'turn/knob')
						(Said 'use/key')
						(Said 'drop/key')
					)
				)
				(Print 19 10)
			)
			((Said 'use/key')
				(if (ego has: iKey)
					(Print 19 15)
				else
					(Print 19 16)
				)
			)
			((or (Said 'unlock/door,lock') (Said 'drop/key'))
				(cond 
					((Btst fMountainDoorOpen)
						(Print 19 12)
					)
					((ego has: iKey)
						(ego loop: 3)
						(RedrawCast)
						(Print 19 13)
						(SolvePuzzle  fUnlockedMountainDoor 2)
						(ego setScript: doorOpens)
					)
					(else
						(Print 19 17)
					)
				)
			)
			((or (Said 'turn/knob') (Said 'close,shut,lock,open,open/door'))
				(if (curRoom script?)
					(CantDo)
				else
					(Print 19 18)
				)
			)
			((Said 'look,check/keyhole')
				(if (ego inRect: 210 115 236 128)
					(ego loop: 3 view: 1 cel: 3)
					(RedrawCast)
					(Print 19 19)
					(NormalEgo)
				else
					(Print 19 20)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 211
		y 113
		view 219
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/door,gate')
				(event claimed: TRUE)
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (ego inRect: 164 110 262 143)
					(Print 19 21)
					(Face ego 213 100)
				else
					(Print 19 22)
				)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		view 219
		loop 1
	)
)

(instance doorOpens of Script
	(properties)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 56 loop: -1 init: play:)
				(= i 0)
				(while (< i (* (+ howFast 1) 5))
					((= [aSparkle i] (Clone sparkle))
						posn: (Random 193 233) (Random 72 111)
						ignoreActors:
						setCycle: ForwardCounter (Random 1 3)
						cycleSpeed: (Random 0 2)
						setPri: 15
						init:
					)
					(++ i)
				)
				(= cycles 9)
			)
			(1
				(door setCycle: EndLoop self)
				(Bset fMountainDoorOpen)
			)
			(2
				((ScriptID 0 21) loop: 1 fade:)
				(door dispose:)
				(= i 0)
				(while (< i (* (+ howFast 1) 5))
					([aSparkle i] dispose:)
					(++ i)
				)
				(if (or (>= (ego x?) 220) (<= (ego x?) 213))
					(ego setMotion: MoveTo 213 119 self)
				else
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 4) self)
				)
			)
			(3
				(ego setLoop: 3 setMotion: MoveTo 252 102 self)
				((ScriptID 0 23) loop: 1 fade:)
			)
			(4
				(HandsOn)
				(curRoom newRoom: 66)
			)
		)
	)
)

(instance doorCloses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 21) number: 56 loop: 1 init: play:)
				(door cel: 8 setCycle: BegLoop self)
			)
			(1
				(door stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance outOfMount of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 215 130 self)
			)
			(1
				(NormalEgo)
				(ego loop: 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance knockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 45
					loop: 0
					cycleSpeed: (if (>= howFast 1) 1 else 0)
					cel: 0
					setMotion: 0
				)
				(= cycles 2)
			)
			(1
				((ScriptID 0 21) number: 29 loop: 2 play: self)
				(ego loop: 1 setCycle: Forward)
			)
			(2
				(ego loop: 0 cel: 0 setCycle: 0)
				(= cycles 2)
			)
			(3
				(ego view: 2 loop: 3 cel: 0)
				(NormalEgo)
				(Print 19 23)
				(self cue:)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbHill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego looper?)
					((ego looper?) dispose:)
				)
				(ego
					illegalBits: 0
					setCycle: Walk
					setLoop: 3
					setPri: 1
					setMotion: MoveTo (ego x?) (+ (ego y?) 15) self
				)
			)
			(1
				(ego stopUpd:)
				(NormalEgo)
				(HandsOn)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance catchUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 19 24)
				(Face ego theGoat)
				(theGoat setMotion: MoveTo 32 121 self)
			)
			(1
				(HandsOn)
				(theGoat setMotion: Follow ego 60)
				(self dispose:)
			)
		)
	)
)

(instance smallTree of NewFeature
	(properties
		x 258
		y 133
		noun '/ceder[<little]'
		nsTop 119
		nsLeft 224
		nsBottom 147
		nsRight 292
		description {small tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is a miniature shade tree, which gives shade to the squirrels and other woodland creatures.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 43
		y 153
		noun '/bush'
		nsTop 144
		nsLeft 21
		nsBottom 163
		nsRight 66
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Long, low bushes grow unbounded throughout Daventry.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 90
		y 152
		noun '/bush'
		nsTop 144
		nsLeft 66
		nsBottom 160
		nsRight 114
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Long, low bushes grow unbounded throughout Daventry.}
	)
)

(instance hill1 of NewFeature
	(properties
		x 224
		y 94
		noun '/hill,hill'
		nsTop 73
		nsLeft 129
		nsBottom 116
		nsRight 320
		description {hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain which overlooks the Daventry countryside.}
	)
)

(instance hill2 of NewFeature
	(properties
		x 246
		y 59
		noun '/hill,hill'
		nsTop 45
		nsLeft 172
		nsBottom 73
		nsRight 320
		description {hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain which overlooks the Daventry countryside.}
	)
)

(instance hill3 of NewFeature
	(properties
		x 265
		y 35
		noun '/hill,hill'
		nsTop 26
		nsLeft 211
		nsBottom 45
		nsRight 320
		description {hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain which overlooks the Daventry countryside.}
	)
)

(instance hill4 of NewFeature
	(properties
		x 278
		y 19
		noun '/hill,hill'
		nsTop 13
		nsLeft 237
		nsBottom 26
		nsRight 319
		description {hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain which overlooks the Daventry countryside.}
	)
)

(instance hill5 of NewFeature
	(properties
		x 294
		y 6
		noun '/hill,hill'
		nsTop -1
		nsLeft 269
		nsBottom 13
		nsRight 320
		description {hill}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain which overlooks the Daventry countryside.}
	)
)

(instance rock of NewFeature
	(properties
		x 95
		y 81
		noun '/boulder'
		nsTop 72
		nsLeft 81
		nsBottom 90
		nsRight 110
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Over the years, many ordinary rocks like this one have tumbled down from the heights of Daventry's mountain.}
	)
)
