;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use LoadMany)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm18 0
)

(local
	local0
)
(instance elfCage of Cage
	(properties
		top 76
		left 1
		bottom 189
		right 319
	)
)

(instance rm18 of Room
	(properties
		picture 18
		horizon 65
		north 31
		east 17
		south 15
		west 19
	)
	
	(method (init)
		(LoadMany VIEW 13 150)
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
				(ego posn: (proc0_17 184 (ego x?) 85) (+ horizon 2))
			)
			(south (ego y: 188))
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 79))
			)
			(east
				(if egoInWater
					(ego posn: 317 (proc0_17 166 (ego y?) 96))
				else
					(ego posn: 317 (proc0_17 188 (ego y?) 170))
				)
			)
			(else 
				(ego posn: 3 137)
				(= local0 1)
			)
		)
		(NormalEgo)
		(if egoInWater
			(ego view: 13)
		)
		(ego init:)
		(self setRegions: WATER)
		(if
			(or
				(and local0 (not (Btst fAttackedElf)) (not (Btst fGotRing)))
				(and (not (Btst fAttackedElf)) (not (Btst fGotRing)) (Random 0 1))
			)
			(elf
				init:
				illegalBits: -32750
				setCycle: Walk
				setMotion: Wander
			)
			(elfCage init:)
			(elf observeBlocks: elfCage)
			(Print 18 0)
		)
		(hill1 init:)
		(hill2 init:)
		(bush init:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'look,check/ceder[<pine]')
					(MouseClaimed event 142 78 201 133)
					(MouseClaimed event 149 66 186 77)
					(MouseClaimed event 159 57 177 66)
					(MouseClaimed event 214 58 276 84)
					(MouseClaimed event 223 40 271 58)
					(MouseClaimed event 228 29 265 40)
					(MouseClaimed event 242 19 258 29)
				)
				(Print 18 1)
			)
			(
				(or
					(Said 'look,check/lake,lake,water,cliff,bank')
					(MouseClaimed event 287 95 320 113)
					(MouseClaimed event 268 113 319 134)
					(MouseClaimed event 239 116 268 128)
					(MouseClaimed event 228 136 320 154)
					(MouseClaimed event 252 154 320 165)
				)
				(Print 18 2)
			)
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'climb,scale/hill')
				(Print 18 3)
			)
			((Said '/elf')
				(cond 
					((Btst fAttackedElf)
						(Print 18 4)
					)
					((cast contains: elf)
						(if (Said 'aid,hug,kiss,touch,get,take/elf')
							(Print 18 5)
						else
							(Print 18 6)
						)
					)
					(else
						(Print 18 7)
					)
				)
			)
			((Said 'look,check>')
				(if (Said '[<at,around][/room,clearing]')
					(if (cast contains: elf)
						(Print 18 8)
					else
						(Print 18 9)
					)
				)
			)
			((Said 'drink,get,take/water,drink')
				(cond 
					((== (ego onControl: origin) cGREEN)
						(Print 18 10)
					)
					((== (ego onControl: origin) cBLUE)
						(Print 18 11)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance elf of Actor
	(properties
		x 100
		y 150
		noun '/elf,man,boy'
		view 150
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(cond 
			((< (= temp0 (ego distanceTo: elf)) 40) (elf setMotion: 0)
				(Face elf ego)
			)
			((< temp0 20)
				(elf dispose:)
				(Print 18 12)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/elf,man,boy') (MousedOn self event shiftDown))
				(Print 18 13)
			)
			((Said 'kiss,hug/elf,man,boy')
				(Print 18 14)
			)
			(
				(or
					(Said 'shoot,kick,stab,punch,kill,kill,attack,attack,bite/elf,man,boy')
					(Said 'throw/dagger')
				)
				(Print 18 15)
				(Bset fAttackedElf)
				(curRoom setScript: elfish)
				(event claimed: TRUE)
			)
			((Said 'throw,throw/boulder,pebble,pebble')
				(if (ego has: iPebbles)
					(Print 18 15)
					(Bset fAttackedElf)
					(curRoom setScript: elfish)
				else
					(Print 18 16)
				)
				(event claimed: TRUE)
			)
			((or (Said 'hello>') (Said 'talk,speak/elf,man,boy') (Said 'say/hello>'))
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((< (ego distanceTo: elf) 30)
						(curRoom setScript: elfish)
						(event claimed: TRUE)
					)
					(else
						(Print 18 17)
						(event claimed: TRUE)
					)
				)
			)
			((Said '/elf,man,boy')
				(Print 18 18)
				(event claimed: TRUE)
			)
			(else
				(event claimed: FALSE)
			)
		)
	)
)

(instance elfish of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fAttackedElf))
					((ScriptID 0 21) number: 105 loop: 1 init: play:)
					(Print 18 19)
					(Print 18 20)
					(Print 18 21)
					(Print 18 22)
					(ego get: iMagicRing)
					(SolvePuzzle fGotRing 3)
				)
				(elf
					setLoop: 4
					cel: 0
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(1
				(elf dispose:)
				(= cycles 1)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hill1 of NewFeature
	(properties
		x 44
		y 32
		noun '/hill'
		nsTop -1
		nsBottom 65
		nsRight 88
		description {mountain}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain that towers above Daventry.}
	)
)

(instance hill2 of NewFeature
	(properties
		x 104
		y 44
		noun '/hill'
		nsTop 34
		nsLeft 87
		nsBottom 54
		nsRight 122
		description {mountain}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are the foothills of a large mountain that towers above Daventry.}
	)
)

(instance bush of NewFeature
	(properties
		x 197
		y 142
		noun '/bush,bury'
		nsTop 133
		nsLeft 169
		nsBottom 151
		nsRight 225
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Small bushes like this one thrive near the fresh lake water.}
	)
)
