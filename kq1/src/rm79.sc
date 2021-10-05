;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Feature)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm79 0
)

(local
	itemOffered
	offeredTreasure
	woodcutterAttention
)
(procedure (CantWhileInvisible)
	(Print 79 66)
)

(instance rm79 of Room
	(properties
		picture 79
	)
	
	(method (init)
		(self style: IRISIN)
		(LoadMany SOUND 8 32)
		(LoadMany VIEW 181 180 279)
		(if (ego has: iCeramicBowl)
			(Load VIEW 35)
		)
		(if
			(or
				(and (Btst fGaveStewToWoodCutter) (not (ego has: iFiddle)))
				(ego has: iCeramicBowl)
			)
			(Load VIEW 1)
		)
		(super init:)
		(NormalEgo)
		(ego posn: 116 160 loop: 3 init:)
		(cupboard init:)
		(fireplace1 init:)
		(fireplace2 init:)
		(fireplace3 init:)
		(fireplace4 init:)
		(nightstand init:)
		(table1 init:)
		(table2 init:)
		(table3 init:)
		(table4 init:)
		(table5 init:)
		(rug1 init:)
		(bed1 init:)
		(bed2 init:)
		(bed3 init:)
		(bed4 init:)
		(bed5 init:)
		(if (not (Btst fGotFiddle))
			(fiddle init: stopUpd:)
		)
		(body ignoreActors: setPri: 8 init: priority: 8 stopUpd:)
		(tableStuff priority: 8)
		(head init: cycleSpeed: 1 stopUpd:)
		(addToPics add: tableStuff eachElementDo: #init doit:)
		(fire init: stopUpd:)
		(if (>= howFast 1)
			(fire cycleSpeed: 1 setCycle: Forward)
		)
		(wife init: stopUpd:)
		(if (not (Btst fInvisible))
			(= woodcutterAttention TRUE)
			(curRoom setScript: welcome)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script
				(script doit:)
			)
			((== (ego onControl: origin) cYELLOW)
				(self newRoom: 44)
			)
		)
	)
	
	(method (dispose)
		(Bset fVisitedWoodcutter)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp i)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event))
			((Said 'open,open,(look,check<in)/cabinet,cabinet')
				(Print 79 0
					#at -1 130
					#width 280
					#mode teJustCenter
				)
			)
			((or (Said 'remove/ring') (Said 'take<off/ring'))
				(cond 
					((not (ego has: iMagicRing))
						(DontHave)
					)
					((not (Btst fWearingRing))
						(event claimed: FALSE)
					)
					(else
						(Bclr fInvisible)
						(Bclr fWearingRing)
						(NormalEgo)
						(ego startUpd:)
						(if (== woodcutterAttention TRUE)
							(Print 79 1
								#at -1 130
								#width 280
								#mode teJustCenter
							)
						else
							(Print 79 2
								#at -1 130
								#width 280
								#mode teJustCenter
							)
							(= woodcutterAttention TRUE)
							(curRoom setScript: welcome)
						)
					)
				)
			)
			((Said 'aid/man,fairy,husband,husband,fairy')
				(if (Btst fGaveStewToWoodCutter)
					(Print 79 3
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				else
					(Print 79 4
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(cond 
							((and (Btst fGaveStewToWoodCutter) (== (body loop?) 4))
								(Print 79 5
									#at -1 20
									#width 280
								)
							)
							((Btst fGaveStewToWoodCutter)
								(Print 79 6
									#at -1 20
									#width 280
								)
							)
							(else
								(Print 79 7
									#at -1 130
									#width 280
									#mode teJustCenter
								)
							)
						)
					)
					((Said 'building,room')
						(Print 79 8)
					)
					((or (Said '<on/endtable') (Said '<on/table<little'))
						(Print 79 9)
					)
					((or (Said '<at/endtable') (Said '<at/table<little'))
						(Print 79 10)
					)
				)
			)
			((Said 'open,open/endtable,(table<little)')
				(Print 79 11)
			)
			((Said 'take/endtable,(table<little)')
				(Print 79 12)
			)
			((Said 'move,pull/endtable,(table<little)')
				(Print 79 13)
			)
			((Said 'stand<on/endtable,(table<little)')
				(Print 79 14)
			)
			((Said 'enter,(get,take,go<in)/bed')
				(Print 79 15)
			)
			((Said 'get,take/fiddle')
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((ego has: iFiddle)
						(Print 79 16
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((not (Btst fGaveStewToWoodCutter))
						(Print 79 17
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					(
						(<=
							(GetDistance
								(ego x?)
								(ego y?)
								(fiddle x?)
								(fiddle y?)
								60
							)
							50
						)
						(curRoom setScript: getFiddle)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'get,take/!*')
				(if (Btst fGaveStewToWoodCutter)
					(Print 79 18
						#at -1 20
						#width 280
					)
				else
					(Print 79 17
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				)
			)
			((Said 'talk,speak/fairy')
				(cond 
					((and (Btst fGaveStewToWoodCutter) (== (body loop?) 4))
						(Print 79 19
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((Btst fGaveStewToWoodCutter)
						(Print 79 20)
					)
					(else
						(Print 79 21
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
				)
			)
			(
			(or (Said 'talk,speak/man,husband') (Said '/hello'))
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(Print 79 22
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((and (Btst fGaveStewToWoodCutter) (not (ego has: iFiddle)))
						(Print 79 23
							#at -1 20
							#width 280
						)
					)
					((Btst fGaveStewToWoodCutter)
						(Print 79 24
							#at -1 20
							#width 280
						)
					)
					(else
						(Print 79 25
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
				)
			)
			((Said 'talk,speak/*')
				(Print 79 26
					#at -1 130
					#width 280
					#mode teJustCenter
				)
			)
			((Said 'talk,speak')
				(Print 79 27
					#at -1 130
					#width 280
					#mode teJustCenter
				)
			)
			((Said 'give[/*]/fairy')
				(Print 79 28)
			)
			((Said 'give/stew')
				(cond 
					((or (not (Btst fBowlHasStew)) (Btst fAteStew))
						(Print 79 29
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((curRoom script?)
						(CantDo)
					)
					((== (ego onControl: origin) cLMAGENTA)
						(Print 79 30)
					)
					((not (ego has: iCeramicBowl))
						(DontHave)
					)
					(else
						(curRoom setScript: fullBowl)
					)
				)
			)
			((Said 'give/bowl')
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((curRoom script?)
						(CantDo)
					)
					((== (ego onControl: origin) cLMAGENTA)
						(Print 79 30)
					)
					((not (ego has: iCeramicBowl))
						(DontHave)
					)
					((and (Btst fBowlHasStew) (not (Btst fAteStew)))
						(curRoom setScript: fullBowl)
					)
					(else
						(curRoom setScript: emptyBowl)
					)
				)
			)
			(
				(and
					(Btst fBowlHasStew)
					(not (Btst fAteStew))
					(Said 'eat,consume/stew')
				)
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((Btst fGaveStewToWoodCutter)
						(Print 79 31
							#at -1 20
							#width 280
						)
					)
					(else
						(Print 79 32
							#at -1 20
							#width 280
						)
					)
				)
			)
			((and (Said 'give>') (= i (inventory firstTrue: #saidMe)))
				(= itemOffered (inventory indexOf: i))
				(= offeredTreasure 0)
				(cond 
					((not (i ownedBy: ego))
						(Print 79 33
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((curRoom script?)
						(CantDo)
					)
					((== (ego onControl: origin) cLMAGENTA)
						(CantReach)
					)
					((Btst fGaveStewToWoodCutter)
						(Print 79 34
							#at -1 20
							#width 280
						)
					)
					(
						(switch itemOffered
							(iChest
								(theGame changeScore: -8)
								(= offeredTreasure iChest)
								(curRoom setScript: acceptIt)
							)
							(iCarrot
								(Bclr fPickedCarrot)
								(theGame changeScore: -2)
								(curRoom setScript: acceptIt)
							)
							(iPouch
								(theGame changeScore: -3)
								(= offeredTreasure TRUE)
								(curRoom setScript: acceptIt)
							)
							(iSceptre
								(theGame changeScore: -6)
								(= offeredTreasure TRUE)
								(curRoom setScript: acceptIt)
							)
							(iCheese
								(theGame changeScore: -2)
								(curRoom setScript: acceptIt)
							)
							(iMagicMirror
								(theGame changeScore: -8)
								(= offeredTreasure TRUE)
								(curRoom setScript: acceptIt)
							)
							(iGoldEgg
								(theGame changeScore: -6)
								(= offeredTreasure TRUE)
								(curRoom setScript: acceptIt)
							)
							(iMagicShield
								(theGame changeScore: -8)
								(= offeredTreasure TRUE)
								(curRoom setScript: acceptIt)
							)
							(iFiddle
								(Print 79 35
									#at -1 130
									#width 280
									#mode teJustCenter
								)
							)
							(iWalnut
								(theGame changeScore: -3)
								(Bclr fGotWalnut)
								(if (Btst fOpenedWalnut)
									(theGame changeScore: -3)
									(= offeredTreasure TRUE)
									(curRoom setScript: acceptIt)
								else
									(Print 79 36
										#at -1 130
										#width 280
										#mode teJustCenter
									)
									(PutInRoom itemOffered)
								)
							)
							(iBeans
								(theGame changeScore: -4)
								(curRoom setScript: acceptIt)
							)
							(else 
								(Print 79 37
									#at -1 130
									#width 280
									#mode teJustCenter
								)
							)
						)
					)
					(else 0)
				)
			)
			((Said 'kill/husband,man,fairy,fairy')
				(Print 79 38)
			)
		)
	)
)

(instance wife of Prop
	(properties
		x 180
		y 116
		description {woodcutter's wife}
		view 181
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/fairy,fairy') (MousedOn self event shiftDown))
				(cond 
					((and (Btst fGaveStewToWoodCutter) (== (body loop?) 4))
						(Print 79 39
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					((Btst fGaveStewToWoodCutter)
						(Print 79 40
							#at -1 130
							#width 280
							#mode teJustCenter
						)
					)
					(else
						(Print 79 41
							#at -1 20
							#width 280
						)
					)
				)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance body of Prop
	(properties
		x 163
		y 139
		description {woodcutter}
		view 180
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(Print 79 42
					#at -1 20
					#width 280
				)
			)
			((Said 'look,check/bowl')
				(if
					(or
						(and (== loop 0) (== cel 1))
						(== loop 3)
						(== loop 4)
					)
					(Print 79 43)
				else
					(event claimed: FALSE)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance head of Prop
	(properties
		x 160
		y 101
		description {woodcutter}
		view 180
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/man,husband,husband') (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(Print 79 42 #at -1 20 #width 280)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance fiddle of View
	(properties
		x 178
		y 161
		z 15
		view 279
		loop 1
		cel 1
		priority 12
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (MousedOn self event shiftDown) (Said 'look,check/fiddle'))
				(Print 79 44
					#at -1 20
					#width 280
				)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 248
		y 141
		noun '/fireplace,fire'
		view 279
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/fireplace,fire') (MousedOn self event shiftDown))
				(Print 79 45
					#at -1 20
					#width 280
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance tableStuff of RPicView
	(properties
		x 70
		y 119
		noun '/pitcher'
		description {pitcher}
		view 279
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'look,check/pitcher')
					(Said 'look,check/table[<little,little]')
				)
				(Print 79 46
					#at -1 20
					#width 280
				)
				(event claimed: TRUE)
			)
			((Said 'look,check<in/pitcher')
				(Print 79 47)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance ceramicBowl of Prop
	(properties
		x 163
		y 119
		z -15
		view 35
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((MousedOn self event shiftDown)
				(Print 79 48
					#at -1 130
					#width 280
					#mode teJustCenter
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance welcome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(head setCycle: EndLoop self)
				(if (not (Btst fGaveStewToWoodCutter))
					((ScriptID 0 23) number: 8 loop: -1 play:)
				)
				(if (Btst fGaveStewToWoodCutter) (wife loop: 1))
			)
			(1
				(head loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(cond 
					((Btst fGaveStewToWoodCutter)
						(Print 79 49
							#at -1 20
							#width 280
						)
						(Print 79 50
							#at -1 20
							#width 280
						)
					)
					((Btst fVisitedWoodcutter)
						(Print 79 51
							#at -1 20
							#width 280
						)
						(Print 79 52
							#at -1 20
							#width 280
						)
					)
					(else
						(Print 79 53
							#at -1 20
							#width 280
						)
						(Print 79 54
							#at -1 20
							#width 280
						)
					)
				)
				(head setCycle: BegLoop self)
			)
			(3
				(head loop: 1 cel: 3 setCycle: BegLoop self)
			)
			(4
				(head stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance emptyBowl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 139 140 self
				)
			)
			(1
				(body hide: stopUpd:)
				(head hide: stopUpd:)
				(ego
					view: 35
					loop: 0
					cel: 0
					posn: 163 139
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(head loop: 2 cel: 0 show: setCycle: EndLoop self)
			)
			(3
				(Print 79 55
					#at -1 130
					#width 280
					#mode teJustCenter
				)
				(head setPri: 11 setCycle: BegLoop self)
			)
			(4
				(head hide: stopUpd:)
				(ego setCycle: BegLoop self)
			)
			(5
				(NormalEgo)
				(ego posn: 139 140 loop: 0 stopUpd:)
				(head show: loop: 1 cel: 3 setCycle: BegLoop self)
				(body show:)
			)
			(6
				(head setPri: -1 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fullBowl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 23) stop:)
				((ScriptID 0 21) number: 32 init: play:)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 139 140 self
				)
			)
			(1
				(body hide: stopUpd:)
				(head hide: stopUpd:)
				(ego
					view: 35
					loop: 0
					cel: 0
					posn: 163 139
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(PutInRoom iCeramicBowl)
			)
			(2
				(NormalEgo)
				(ego posn: 139 140 loop: 0 stopUpd:)
				(body loop: 0 cel: 1 show:)
				(head show: loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(SolvePuzzle fGaveStewToWoodCutter 3)
				(Print 79 56
					#at -1 20
					#width 280
				)
				(Print 79 57
					#at -1 20
					#width 280
				)
				(head setPri: 11 setCycle: BegLoop self)
			)
			(4
				(head hide: stopUpd:)
				(wife loop: 1 forceUpd: stopUpd:)
				(body loop: 4 cel: 0 cycleSpeed: 2 setCycle: Forward)
				(= cycles 62)
			)
			(5
				(body loop: 0 cel: 1 setCycle: 0)
				(head show: setCycle: EndLoop self)
			)
			(6
				(Print 79 58
					#at -1 20
					#width 280
				)
				(head hide: stopUpd:)
				(body loop: 3 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(7 (= seconds 3))
			(8
				(body loop: 4 setPri: 8 setCycle: Forward)
				(HandsOn)
				(Bset fGaveStewToWoodCutter)
				(self dispose:)
			)
		)
	)
)

(instance getFiddle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 2)
				(= cycles 2)
			)
			(1
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(SolvePuzzle fGotFiddle 3)
				(ego get: iFiddle)
				(fiddle dispose:)
				(= cycles 8)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance acceptIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 139 140 self
				)
			)
			(1
				(Face ego body)
				(if offeredTreasure
					(Print 79 59
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				else
					(Print 79 60
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				)
				(head loop: 2 cel: 0 setCycle: EndLoop self)
				(PutInRoom itemOffered)
			)
			(2
				(ego stopUpd:)
				(if offeredTreasure
					(Print 79 61
						#at -1 20
						#width 280
					)
				else
					(Print 79 62
						#at -1 130
						#width 280
						#mode teJustCenter
					)
				)
				(head setPri: 11 setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(ego posn: 139 140 loop: 0)
				(head show: loop: 1 cel: 3 setCycle: BegLoop self)
				(body show: stopUpd:)
				(ceramicBowl dispose:)
			)
			(4
				(head setPri: -1 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cupboard of NewFeature
	(properties
		x 68
		y 91
		noun '/cabinet,cabinet'
		nsTop 82
		nsLeft 55
		nsBottom 101
		nsRight 81
		description {cupboard}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a rickety old cupboard on the wall.}
	)
)

(instance fireplace1 of NewFeature
	(properties
		x 251
		y 46
		noun '/fireplace,chimney'
		nsTop 5
		nsLeft 241
		nsBottom 88
		nsRight 262
		description {chimney}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large fire burns merrily in the fireplace, providing some warmth in the drafty old cottage.}
	)
)

(instance fireplace2 of NewFeature
	(properties
		x 251
		y 99
		noun '/fireplace,chimney'
		nsTop 88
		nsLeft 236
		nsBottom 110
		nsRight 266
		description {fireplace}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large fire burns merrily in the fireplace, providing some warmth in the drafty old cottage.}
	)
)

(instance fireplace3 of NewFeature
	(properties
		x 234
		y 116
		noun '/fireplace,chimney'
		nsTop 98
		nsLeft 228
		nsBottom 135
		nsRight 241
		description {fireplace}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large fire burns merrily in the fireplace, providing some warmth in the drafty old cottage.}
	)
)

(instance fireplace4 of NewFeature
	(properties
		x 266
		y 121
		noun '/fireplace,chimney'
		nsTop 102
		nsLeft 257
		nsBottom 141
		nsRight 275
		description {fireplace}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A large fire burns merrily in the fireplace, providing some warmth in the drafty old cottage.}
	)
)

(instance table1 of NewFeature
	(properties
		x 147
		y 144
		noun '/table[<big,kitchen]'
		nsTop 139
		nsLeft 143
		nsBottom 150
		nsRight 151
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
	)
	
	(method (doLook)
		(if ((inventory at: iFiddle) ownedBy: curRoomNum)
			(Print 79 63)
		else
			(Print 79 64)
		)
	)
)

(instance table2 of NewFeature
	(properties
		x 158
		y 145
		noun '/table[<big,kitchen]'
		nsTop 138
		nsLeft 151
		nsBottom 153
		nsRight 165
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
	)
	
	(method (doLook)
		(if ((inventory at: iFiddle) ownedBy: curRoomNum)
			(Print 79 63)
		else
			(Print 79 64)
		)
	)
)

(instance table3 of NewFeature
	(properties
		x 176
		y 144
		noun '/table[<big,kitchen]'
		nsTop 135
		nsLeft 165
		nsBottom 154
		nsRight 188
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
	)
	
	(method (doLook)
		(if ((inventory at: iFiddle) ownedBy: curRoomNum)
			(Print 79 63)
		else
			(Print 79 64)
		)
	)
)

(instance table4 of NewFeature
	(properties
		x 197
		y 143
		noun '/table[<big,kitchen]'
		nsTop 136
		nsLeft 188
		nsBottom 150
		nsRight 206
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
	)
	
	(method (doLook)
		(if ((inventory at: iFiddle) ownedBy: curRoomNum)
			(Print 79 63)
		else
			(Print 79 64)
		)
	)
)

(instance table5 of NewFeature
	(properties
		x 211
		y 144
		noun '/table[<big,kitchen]'
		nsTop 141
		nsLeft 206
		nsBottom 148
		nsRight 216
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
	)
	
	(method (doLook)
		(if ((inventory at: iFiddle) ownedBy: curRoomNum)
			(Print 79 63)
		else
			(Print 79 64)
		)
	)
)

(instance rug1 of NewFeature
	(properties
		x 123
		y 148
		noun '/carpet,carpet'
		nsTop 138
		nsLeft 103
		nsBottom 158
		nsRight 143
		description {rug}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a small, threadbare carpet on the floor.}
	)
)

(instance bed1 of Feature
	(properties
		x 112
		y 117
		noun '/bed'
		nsTop 103
		nsLeft 94
		nsBottom 132
		nsRight 130
		description {bed}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 79 65))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bed2 of Feature
	(properties
		x 96
		y 94
		nsTop 87
		nsLeft 93
		nsBottom 102
		nsRight 99
		description {bed}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (doVerb theVerb)
		(bed1 doVerb: theVerb)
	)
)

(instance bed3 of Feature
	(properties
		x 173
		y 118
		nsTop 107
		nsLeft 131
		nsBottom 129
		nsRight 215
		description {bed}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (doVerb theVerb)
		(bed1 doVerb: theVerb)
	)
)

(instance bed4 of Feature
	(properties
		x 146
		y 133
		nsTop 129
		nsLeft 131
		nsBottom 137
		nsRight 162
		description {bed}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (doVerb theVerb)
		(bed1 doVerb: theVerb)
	)
)

(instance bed5 of Feature
	(properties
		x 194
		y 94
		nsTop 82
		nsLeft 172
		nsBottom 106
		nsRight 216
		description {bed}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (doVerb theVerb)
		(bed1 doVerb: theVerb)
	)
)

(instance nightstand of NewFeature
	(properties
		x 71
		y 123
		noun '/endtable,(table<little)'
		nsTop 113
		nsLeft 46
		nsBottom 134
		nsRight 96
		description {nightstand}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The old nightstand is nothing more than a small plain table at the foot of the bed.}
	)
)
