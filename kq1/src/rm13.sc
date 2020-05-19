;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Chase)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm13 0
)

(local
	egoX
	egoY
	local2
)
(instance rm13 of Room
	(properties
		picture 13
		horizon 72
		north 20
		east 14
		south 4
		west 12
		picAngle 60
	)
	
	(method (init)
		(LoadMany VIEW 125 20)
		(if (not (ego has: iMagicShield))
			(LoadMany SOUND 41 46)
		)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_18 127 (ego x?) 12) (+ horizon 2))
			)
			(south
				(ego y: 188)
			)
			(west
				(ego
					posn: 3 (proc0_17
						189
						(proc0_18 112 (ego y?) 103)
						(+ horizon 2)
					)
				)
			)
			(east
				(ego
					posn: 317 (proc0_17 189 (proc0_18 175 (ego y?) 165) 149)
				)
			)
			(else 
				(ego
					posn: 317 (proc0_17 189 (proc0_18 175 (ego y?) 165) 149)
				)
				(= local2 1)
			)
		)
		(ego init:)
		(NormalEgo)
		(rock1 init:)
		(rock2 init:)
		(rock3 init:)
		(rock4 init:)
		(rock5 init:)
		(rock6 init:)
		(bush1 init:)
		(rock7 init:)
		(tree4 init:)
		(tree3 init:)
		(tree2 init:)
		(tree1 init:)
		(tree init:)
		((ScriptID 0 21) number: 91 loop: 1 init: play:)
		(if (or (Random 0 1) local2) (self setScript: waitOne))
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and script isHandsOff)
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
			(script
				(script doit:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(Print 13 0)
					)
					((Said '/dog')
						(Print 13 1)
					)
				)
			)
			(
			(or (Said 'use,shoot/shot,shot') (Said 'shoot/dog'))
				(cond 
					((and (ego has: iPebbles) (ego has: iSlingshot) (cast contains: theMenace))
						(Print 13 2)
					)
					((and (ego has: iPebbles) (not (ego has: iSlingshot)) (cast contains: theMenace))
						(Print 13 3)
					)
					((and (not (ego has: iPebbles)) (ego has: iSlingshot))
						(Print 13 4)
					)
					((not (cast contains: theMenace))
						(Print 13 5)
					)
					(else
						(Print 13 6)
					)
				)
			)
			((Said 'throw,throw/pebble,pebble,boulder')
				(cond 
					((ego has: iPebbles)
						(Print 13 7)
					)
					((not (cast contains: theMenace))
						(Print 13 8)
					)
					(else
						(Print 13 9)
					)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance rock1 of NewFeature
	(properties
		x 66
		y 69
		noun '/boulder'
		nsTop 54
		nsLeft 33
		nsBottom 84
		nsRight 99
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 194
		y 138
		noun '/boulder'
		nsTop 126
		nsLeft 155
		nsBottom 150
		nsRight 234
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock3 of NewFeature
	(properties
		x 203
		y 120
		noun '/boulder'
		nsTop 114
		nsLeft 172
		nsBottom 126
		nsRight 235
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock4 of NewFeature
	(properties
		x 223
		y 106
		noun '/boulder'
		nsTop 98
		nsLeft 197
		nsBottom 114
		nsRight 250
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock5 of NewFeature
	(properties
		x 266
		y 92
		noun '/boulder'
		nsTop 87
		nsLeft 214
		nsBottom 98
		nsRight 319
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance rock6 of NewFeature
	(properties
		x 270
		y 76
		noun '/boulder'
		nsTop 65
		nsLeft 252
		nsBottom 87
		nsRight 288
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 277
		y 124
		noun '/bush'
		nsTop 104
		nsLeft 235
		nsBottom 145
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You see lush plants and bushes throughout the Kingdom of Daventry.}
	)
)

(instance rock7 of NewFeature
	(properties
		x 287
		y 168
		noun '/boulder'
		nsTop 157
		nsLeft 255
		nsBottom 179
		nsRight 320
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is one of the countless rocks and boulders that dot the countryside.}
	)
)

(instance wolfStuff of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(theGoat
			setLoop: (if (< (theGoat heading?) 180) 0 else 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 13 15)
				((ScriptID 0 23) number: 41 loop: -1 play:)
				(theMenace
					view: 125
					setCycle: Walk
					setAvoider: Avoider
					setStep: 8 6
					setPri: -1
					setLoop: -1
					ignoreActors:
					init:
				)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(cond 
					((and (>= egoX 130) (<= egoY 97)) (theMenace posn: 340 75))
					((<= egoY 148) (theMenace posn: -20 150))
					((<= egoX 142) (theMenace posn: -20 170))
					((<= egoY 170) (theMenace posn: 340 163))
					(else (theMenace posn: 340 163))
				)
				(self cue:)
			)
			(1
				(theMenace setMotion: Chase ego 42 self)
			)
			(2
				(HandsOff)
				(theMenace hide:)
				(ego setMotion: 0 view: 20 cel: 0 loop: 0 setCycle: Forward)
				(if (== (curRoom script?) (ScriptID 782 0))
					((ScriptID 782 1) stop:)
				)
				((ScriptID 0 21) number: 46 loop: 1 init: play: self)
			)
			(3
				(ego loop: 1 cel: 0 setCycle: EndLoop)
				(theMenace
					loop: 2
					posn: (ego x?) (ego y?)
					show:
					setPri: (ego priority?)
				)
				(if (Btst fInvisible)
					(Print 13 16)
				)
				(= cycles 38)
			)
			(4
				((ScriptID 0 23) fade:)
				(EgoDead
					{How many times have you been told not to wolf down your food?}
				)
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
			((Said 'kill,shoot,attack/dog')
				(Print 13 10)
			)
			((Said 'capture,get,take,take/dog')
				(Print 13 11)
			)
			((Said 'eat,consume/dog')
				(Print 13 12)
			)
			((Said 'ride/dog')
				(Print 13 13)
			)
			((or (Said 'look,check/dog') (MousedOn theMenace event))
				(Print 13 14)
				(event claimed: TRUE)
			)
		)
	)
)

(instance waitOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(if
					(and
						(not (Btst fGoatFollows))
						(ego inRect: 25 (+ (curRoom horizon?) 12) 295 176)
						(not (Btst fInvisible))
						(not haloTimer)
						(not (ego has: 16))
						(not (theMenace script?))
						(or (> (ego x?) 130) (> (ego y?) 85))
					)
					(theMenace setScript: wolfStuff)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance tree4 of NewFeature
	(properties
		x 26
		y 27
		noun '/ceder'
		nsTop -1
		nsBottom 55
		nsRight 52
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of gorgeous trees dot the Daventry countryside.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 10
		y 82
		noun '/ceder'
		nsTop 55
		nsBottom 109
		nsRight 21
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of gorgeous trees dot the Daventry countryside.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 216
		y 13
		noun '/ceder'
		nsTop -1
		nsLeft 114
		nsBottom 27
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of gorgeous trees dot the Daventry countryside.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 222
		y 39
		noun '/ceder'
		nsTop 27
		nsLeft 124
		nsBottom 52
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of gorgeous trees dot the Daventry countryside.}
	)
)

(instance tree of NewFeature
	(properties
		x 236
		y 57
		noun '/ceder'
		nsTop 52
		nsLeft 153
		nsBottom 63
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {All sorts of gorgeous trees dot the Daventry countryside.}
	)
)
