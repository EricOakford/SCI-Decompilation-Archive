;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use StopWalk)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm30 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	[local11 35]
)
(instance rm30 of Room
	(properties
		picture 30
		horizon 80
		north 35
		east 31
		south 19
		west 29
	)
	
	(method (init &tmp temp0 temp1)
		(asm
			pushi    31
			pushi    1
			lsg      prevRoomNum
			dup     
			pToa     north
			eq?     
			bnt      code_001f
			ldi      5
			jmp      code_0040
code_001f:
			dup     
			pToa     west
			eq?     
			bnt      code_002b
			ldi      3
			jmp      code_0040
code_002b:
			dup     
			pToa     east
			eq?     
			bnt      code_0037
			ldi      2
			jmp      code_0040
code_0037:
			dup     
			pToa     south
			eq?     
			bnt      code_0040
			ldi      4
code_0040:
			toss    
			push    
			self     6
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_0056
			pushi    2
			pushi    128
			pushi    205
			callk    Load,  4
code_0056:
			pushi    2
			pushi    128
			pushi    230
			callk    Load,  4
			pushi    #has
			pushi    1
			pushi    18
			lag      ego
			send     6
			not     
			bnt      code_0076
			pushi    2
			pushi    128
			pushi    1
			callk    Load,  4
code_0076:
			pushi    #init
			pushi    0
			super    Room,  4
			lsg      prevRoomNum
			dup     
			pToa     north
			eq?     
			bnt      code_00b5
			pushi    225
			pushi    #-info-
			pushi    3
			pushi    319
			pushi    3
			pushi    272
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    248
			callb    proc0_18,  6
			push    
			pushi    41
			callb    proc0_17,  6
			push    
			pTos     horizon
			ldi      2
			add     
			push    
			lag      ego
			send     8
			jmp      code_0142
code_00b5:
			dup     
			pToa     south
			eq?     
			bnt      code_00c9
			pushi    #y
			pushi    1
			pushi    188
			lag      ego
			send     6
			jmp      code_0142
code_00c9:
			dup     
			pToa     west
			eq?     
			bnt      code_00fe
			pushi    225
			pushi    #-info-
			pushi    3
			dup     
			pushi    188
			pushi    3
			pushi    130
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    125
			callb    proc0_18,  6
			push    
			pTos     horizon
			ldi      2
			add     
			push    
			callb    proc0_17,  6
			push    
			lag      ego
			send     8
			jmp      code_0142
code_00fe:
			dup     
			pToa     east
			eq?     
			bnt      code_0135
			pushi    225
			pushi    #-info-
			pushi    317
			pushi    3
			pushi    178
			pushi    3
			pushi    141
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    113
			callb    proc0_18,  6
			push    
			pTos     horizon
			ldi      2
			add     
			push    
			callb    proc0_17,  6
			push    
			lag      ego
			send     8
			jmp      code_0142
code_0135:
			pushi    #posn
			pushi    2
			pushi    3
			pushi    137
			lag      ego
			send     8
code_0142:
			toss    
			pushi    #init
			pushi    0
			lag      ego
			send     4
			pushi    0
			callb    NormalEgo,  0
			pushi    #init
			pushi    0
			lofsa    tree
			send     4
			pushi    #init
			pushi    0
			lofsa    tree1
			send     4
			pushi    #init
			pushi    0
			lofsa    tree2
			send     4
			pushi    #init
			pushi    0
			lofsa    pineTree1
			send     4
			pushi    #init
			pushi    0
			lofsa    trunk
			send     4
			pushi    #init
			pushi    0
			lofsa    smallBush
			send     4
			pushi    #init
			pushi    0
			lofsa    farTree
			send     4
			pushi    #init
			pushi    0
			lofsa    pineTree2
			send     4
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_01c3
			pushi    #setStep
			pushi    2
			pushi    5
			dup     
			pushi    131
			pushi    1
			class    Walk
			push    
			pushi    241
			pushi    1
			class    Avoider
			push    
			pushi    231
			pushi    0
			pushi    18
			pushi    1
			pushi    0
			pushi    242
			pushi    0
			pushi    93
			pushi    0
			pushi    226
			pushi    0
			lofsa    squirrel
			send     42
code_01c3:
			pushi    1
			lag      howFast
			add     
			push    
			ldi      8
			mul     
			sal      local0
			ldi      0
			sal      local6
code_01d1:
			lsl      local6
			lal      local0
			lt?     
			bnt      code_023e
			pushi    1
			lofsa    nutView
			push    
			callk    Clone,  2
			push    
			lal      local6
			sali     local11
code_01e6:
			pushi    36
			pushi    2
			pushi    10
			pushi    190
			callk    Random,  4
			sat      temp0
			lt?     
			bnt      code_0202
			pprev   
			ldi      130
			lt?     
			bnt      code_0202
			jmp      code_01e6
code_0202:
			pushi    164
			pushi    2
			pushi    145
			pushi    185
			callk    Random,  4
			sat      temp1
			lt?     
			bnt      code_0220
			pprev   
			ldi      172
			lt?     
			bnt      code_0220
			jmp      code_0202
code_0220:
			pushi    #posn
			pushi    2
			lst      temp0
			lst      temp1
			pushi    93
			pushi    0
			pushi    231
			pushi    0
			pushi    226
			pushi    0
			lal      local6
			lali     local11
			send     20
			+al      local6
			jmp      code_01d1
code_023e:
			ret     
		)
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
					((or (Said '<around,down') (Said '/grass'))
						(if (< local10 local0)
							(if (ego inRect: 10 145 190 185)
								(Print 30 0)
							else
								(Print 30 1)
							)
						else
							(Print 30 2)
						)
					)
					((Said '[<at,around][/room,clearing,ceder]')
						(Print 30 3)
					)
					((Said '/ceder<pine')
						(Print 30 4)
					)
					((or (Said '/ceder') (Said '/ceder<nut'))
						(if (ego inRect: 10 145 190 185)
							(Print 30 5)
						else
							(Print 30 3)
						)
					)
					((Said '/nut,nut')
						(cond 
							((ego has: iWalnut)
								((inventory at: iWalnut) showSelf: ego)
							)
							((< local10 local0)
								(if (ego inRect: 10 145 190 185)
									(Print 30 0)
								else
									(Print 30 6)
								)
							)
							(else (Print 30 2))
						)
					)
				)
			)
			((Said 'pick,get,take/nut<gold')
				(cond 
					((Btst fGotWalnut)
						(Print 30 7)
					)
					((and (ego has: iWalnut) (Btst fGotWalnut))
						(Print 30 8)
					)
					(else
						(Print 30 9)
					)
				)
			)
			((Said 'pick,get,take/nut,nut')
				(cond 
					((Btst fGotWalnut)
						(Print 30 10)
					)
					((ego has: iWalnut)
						(Print 30 11)
					)
					((Btst fInvisible)
						(Print 30 12)
					)
					((>= local10 local0)
						(Print 30 2)
					)
					(else
						(ego setScript: getWalnut)
					)
				)
			)
		)
	)
)

(class nutView of View
	(properties
		description {nut}
		sightAngle 90
		closeRangeDist 50
		longRangeDist 100
		view 230
		loop 0
		cel 0
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((MousedOn self event shiftDown)
				(Print 30 13)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance squirrel of Actor
	(properties
		noun '/squirrel'
		closeRangeDist 320
		longRangeDist 320
		shiftClick 1
		view 205
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(< (Random 1 100) 3)
				(not (squirrel script?))
				(not local2)
			)
			(squirrel setScript: squirrelDash)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'throw,throw/boulder,pebble,pebble[/squirrel]')
				(cond 
					((or (< x 0) (> x 320))
						(Print 30 14)
					)
					((ego has: iPebbles)
						(Print 30 15)
						(PebbleCount)
					)
					(else
						(Print 30 16)
					)
				)
			)
			((Said 'give/nut,nut/squirrel')
				(if (or (< x 0) (> x 320))
					(Print 30 17)
				else
					(Print 30 18)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'look,check/squirrel'))
				(if
				(and (> (squirrel x?) 0) (< (squirrel x?) 320))
					(Print 30 19)
				else
					(Print 30 20)
				)
			)
			((Said 'get,take/squirrel')
				(if (or (< x 0) (> x 320))
					(Print 30 17)
				else
					(Print 30 21)
				)
			)
			((Said 'shoot/squirrel[/shot]')
				(cond 
					((not (ego has: iSlingshot))
						(Print 30 22)
					)
					((not (ego has: iPebbles))
						(Print 30 23)
					)
					(else
						(Print 30 24)
					)
				)
			)
			((Said 'kill,shoot/squirrel')
				(Print 30 25)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance squirrelDash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< local10 local0)
					(= local4 ([local11 local10] y?))
					(= local3 ([local11 local10] x?))
				else
					(if (Random 0 1)
						(= local4 (Random 145 163))
					else
						(= local4 (Random 169 185))
					)
					(if (Random 0 1)
						(= local3 (Random 10 47))
					else
						(= local3 (Random 118 190))
					)
				)
				(if (< local3 83)
					(squirrel posn: -30 local4)
					(= local5 1)
				else
					(squirrel posn: 350 (Random 140 170))
					(= local5 0)
				)
				(squirrel setMotion: MoveTo local3 local4 self)
			)
			(1
				(if (< local10 local0)
					([local11 local10] posn: -1 -1)
					([local11 local10] hide:)
					(++ local10)
				)
				(if local5
					(squirrel setMotion: MoveTo -30 local4 self)
				else
					(squirrel setMotion: MoveTo 350 (Random 140 170) self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance getWalnut of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(> state 0)
				(== ([local11 local8] x?) -1)
				(not local1)
			)
			(if (< local10 local0)
				(Print 30 26)
				(Print 30 27)
				(= local1 1)
				(self changeState: 0)
			else
				(Print 30 28)
				(self changeState: 5)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local7 9999)
				(= local6 0)
				(while (< local6 local0)
					(if
					(< (= local9 (ego distanceTo: [local11 local6])) local7)
						(= local7 local9)
						(= local8 local6)
					)
					(++ local6)
				)
				(if (< (ego distanceTo: [local11 local8]) 25)
					(self cue:)
				else
					(Print 30 29)
					(HandsOn)
					(self dispose:)
				)
			)
			(1
				(if (< (ego x?) ([local11 local8] x?))
					(ego
						view: 0
						setCycle: StopWalk 2
						setAvoider: (Avoider offScreenOK: 0)
						setMotion:
							MoveTo
							(- ([local11 local8] x?) 7)
							([local11 local8] y?)
							self
					)
				else
					(ego
						view: 0
						setCycle: StopWalk 2
						setAvoider: Avoider
						setMotion:
							MoveTo
							(+ ([local11 local8] x?) 7)
							([local11 local8] y?)
							self
					)
				)
			)
			(2
				(ego
					view: 1
					loop: (if (< (ego x?) ([local11 local8] x?)) 0 else 1)
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 30 30)
				(ego get: iWalnut)
				([local11 local8] hide:)
				(SolvePuzzle fGotWalnut 3)
				(= cycles 4)
				(ego setAvoider: 0)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree of NewFeature
	(properties
		x 104
		y 36
		noun '/ceder[<nut]'
		nsBottom 72
		nsRight 209
		description {walnut tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 84
		y 115
		noun '/ceder[<nut]'
		nsTop 72
		nsLeft 67
		nsBottom 158
		nsRight 102
		description {walnut tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 82
		y 162
		noun '/ceder[<nut]'
		nsTop 157
		nsLeft 56
		nsBottom 168
		nsRight 108
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 500
		shiftClick 369
		contClick 371
		lookStr {This large, healthy tree is chock full of plump walnuts.}
	)
)

(instance pineTree1 of NewFeature
	(properties
		x 272
		y 103
		noun '/ceder[<pine]'
		nsTop 71
		nsLeft 239
		nsBottom 135
		nsRight 306
		description {pineTree1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are just two garden-variety pine trees, with nothing to offer but shade.}
	)
)

(instance pineTree2 of NewFeature
	(properties
		x 265
		y 50
		noun '/ceder[<pine]'
		nsTop 31
		nsLeft 246
		nsBottom 70
		nsRight 284
		description {pine tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These are just two garden-variety pine trees, with nothing to offer but shade.}
	)
)

(instance trunk of NewFeature
	(properties
		x 20
		y 99
		noun '/chest'
		nsTop 72
		nsLeft 6
		nsBottom 127
		nsRight 35
		description {trunk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's nothing particularly interesting about this old gray tree.}
	)
)

(instance smallBush of NewFeature
	(properties
		x 255
		y 171
		noun '/chest'
		nsTop 159
		nsLeft 213
		nsBottom 183
		nsRight 298
		description {small bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Some typical Daventry bushes flourish in the shade of the nearby walnut tree.}
	)
)

(instance farTree of NewFeature
	(properties
		x 269
		y 13
		noun '/chest'
		nsTop -1
		nsLeft 220
		nsBottom 27
		nsRight 318
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are trees growing nearly everywhere you look in Daventry.}
	)
)
