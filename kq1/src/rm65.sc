;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use Feature)
(use LoadMany)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm65 0
)

(local
	witchComingHomeTimer
	local1
)
(procedure (CantWhileInvisible)
	(Print 65 58
		#at -1 130
		#width 280
		#mode teJustCenter
	)
)

(procedure (NeedToGetCloser)
	(Print 65 59
		#at -1 130
		#width 280
		#mode teJustCenter
	)
)

(procedure (InOtherRoom)
	(Print 65 60
		#at -1 130
		#width 280
		#mode teJustCenter
	)
)

(procedure (InBedroom)
	(return (& (ego onControl: origin) cLMAGENTA))
)

(procedure (InCabinetRect)
	(ego inRect: 100 61 124 82)
)

(instance stoolBlock of Block
	(properties
		top 88
		left 88
		bottom 100
		right 112
	)
)

(instance rm65 of Room
	(properties
		picture 65
		north 80
		west 28
	)
	
	(method (init)
		(LoadMany VIEW 119 65 52 115 118 265)
		(LoadMany SOUND 88 86 87 85 84 40)
		(self style: WIPERIGHT)
		(super init:)
		(SetCursor waitCursor FALSE 298 1)
		(ego posn: 36 126 loop: 0 init:)
		(NormalEgo)
		((ScriptID 0 23) number: 88 loop: -1 play:)
		(stool init: stopUpd:)
		(pot init:)
		(if (not (Btst fGotNote))
			(note setPri: 7 init: stopUpd:)
		)
		(bed init:)
		(bed2 init:)
		(bedroomTable init:)
		(table init:)
		(bars init:)
		(spiderWeb init:)
		(door
			init:
			cel: (if (Btst fCupboardOpen) 3 else 0)
			setPri: 2
			stopUpd:
		)
		(if (not (Btst fGotCheese))
			(rmCheese setPri: 1 init: stopUpd:)
		)
		(fire init: stopUpd:)
		(bubble init: stopUpd:)
		(smoke setPri: 4 init: stopUpd:)
		(if (>= howFast 1)
			(fire setCycle: Forward)
			(bubble setCycle: Forward)
			(smoke setScript: smokeFace)
		)
		(if (Btst fKilledWitch)
			(goop init:)
		)
		(if (Btst fGoatFollows)
			(Print 65 0)
			(Bclr fGoatFollows)
		)
		(cond 
			((Btst fWitchIsHome)
				(witch init: setCycle: Walk)
				(witch setScript: witchHome)
			)
			((Btst fKilledWitch)
				0
			)
			((and (not script) (not haloTimer))
				(= witchComingHomeTimer 300)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			((Btst fInvisible)
				(Bclr fInvisible)
				(NormalEgo)
				(Print 65 1)
			)
			((Btst fPushingWitch)
				(Bclr fPushingWitch)
				(ego ignoreBlocks: stoolBlock)
			)
			((& (ego onControl: origin) cGREEN)
				(SetCursor waitCursor FALSE 153 150)
				(self newRoom: 28)
			)
			(script
				(script doit:)
			)
			((== witchComingHomeTimer 100)
				((ScriptID 0 21) number: 84 init: loop: 1 play:)
				(Print 65 2)
				(-- witchComingHomeTimer)
			)
			((> witchComingHomeTimer 1)
				(-- witchComingHomeTimer)
			)
			((and (== witchComingHomeTimer 1) (not (& (ego onControl: origin) cYELLOW)))
				(= witchComingHomeTimer 0)
				(witch setScript: witchEnter)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'play/fiddle')
				(cond 
					((not (cast contains: witch))
						(event claimed: FALSE)
					)
					((not (ego has: iFiddle))
						(DontHave)
					)
					(else
						(Print 65 3)
					)
				)
			)
			((Said 'eat,consume/building')
				(Print 65 4)
			)
			((Said '/witch>')
				(cond 
					((Btst fKilledWitch)
						(Print 65 5)
						(event claimed: TRUE)
					)
					((not (cast contains: witch))
						(Print 65 6 #at -1 130 #width 280 #mode teJustCenter)
						(event claimed: TRUE)
					)
					((Said 'pull')
						(Print 65 7)
					)
					((Said 'kill')
						(Print 65 8)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'fill/bucket')
				(if (ego has: iWaterBucket)
					(Print 65 9)
				else
					(Print 65 10)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[/room,building]')
						(Print 65 11 #at -1 130 #width 280 #mode teJustCenter)
					)
					((Said '/bedroom')
						(if (InBedroom)
							(Print 65 12 #at -1 130 #width 280 #mode teJustCenter)
						else
							(Print 65 13 #at -1 130 #width 280 #mode teJustCenter)
						)
					)
					((Said '<in/cage')
						(Print 65 14 #at -1 130 #width 280 #mode teJustCenter)
					)
					((Said '/table')
						(if (InBedroom)
							(bedroomTable doLook: TRUE)
						else
							(table doLook: TRUE)
						)
					)
				)
			)
			((Said 'eat,consume,drink/liquid,brew')
				(Print 65 15 #at -1 130 #width 280 #mode teJustCenter)
			)
			((Said 'smell,sniff')
				(Print 65 16 #at -1 130 #width 280 #mode teJustCenter)
			)
			((Said 'open,open/cage')
				(Print 65 17)
			)
			((Said 'bend/bar')
				(Print 65 18 #at -1 130 #width 280 #mode teJustCenter)
			)
			((Said 'open,open/cabinet,door')
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((Btst fCupboardOpen)
						(Print 65 19)
					)
					(script
						(CantDo)
					)
					((InCabinetRect)
						(self setScript: openCabinet)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'close,shut/cabinet,door')
				(cond 
					((not (InCabinetRect))
						(CantReach)
					)
					(script
						(CantDo)
					)
					((Btst fCupboardOpen)
						(self setScript: closeCabinet)
					)
					(else
						(Print 65 20)
					)
				)
			)
			((Said 'get,take/cheese')
				(cond 
					((Btst fGotCheese)
						(Print 65 21)
					)
					((not (Btst fCupboardOpen))
						(Print 65 22)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					(script
						(CantDo)
					)
					((InCabinetRect)
						(self setScript: getCheese)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'feel/caldron')
				(cond 
					((InBedroom)
						(InOtherRoom)
					)
					((ego inRect: 15 70 110 110)
						(Print 65 23 #at -1 130 #width 280 #mode teJustCenter)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'climb,scale,dive,jump<in,in/caldron')
				(cond 
					((InBedroom)
						(InOtherRoom)
					)
					((ego inRect: 15 70 110 110)
						(Print 65 24 #at -1 130 #width 280 #mode teJustCenter)
					)
					(else
						(CantReach)
					)
				)
			)
			((or (Said 'nap') (Said 'go/nap') (Said 'take/nap'))
				(if (InBedroom)
					(Print 65 25 #at -1 130 #width 280 #mode teJustCenter)
				else
					(Print 65 26 #at -1 130 #width 280 #mode teJustCenter)
				)
			)
			((Said 'rub/ring')
				(Print 65 27 #at -1 130 #width 280 #mode teJustCenter)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance witch of Actor
	(properties
		x 94
		y 85
		view 115
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'talk,speak,say/witch')
				(Print 65 28 #at -1 130 #width 280 #mode teJustCenter)
			)
			((Said 'look,check/witch')
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(if (and (InBedroom) (>= argc 2))
					(Print 65 29)
				else
					(Print 65 30)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 43
		y 82
		view 265
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/fire')
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (and (>= argc 2) (InBedroom))
					(InOtherRoom)
				else
					(Print 65 31 #at -1 130 #width 280 #mode teJustCenter)
				)
			)
		)
	)
)

(instance bubble of Prop
	(properties
		x 62
		y 67
		view 265
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/liquid,brew') (MousedOn self event shiftDown))
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((and (InBedroom) (== (event type?) saidEvent))
						(InOtherRoom)
					)
					((and (not (ego inRect: 15 70 110 110)) (== (event type?) saidEvent))
						(NeedToGetCloser)
					)
					(else
						(Print 65 32 #at -1 120 #width 280 #mode teJustCenter)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 62
		y 62
		noun '/smoke'
		view 265
		loop 2
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/smoke')
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((InBedroom)
						(Print 65 33 #at -1 130 #width 280 #mode teJustCenter)
					)
					((curRoom script?)
						(CantDo)
					)
					(else
						(smoke setScript: seeSpectre)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 119
		y 33
		view 265
		loop 3
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/cabinet,shelf') (MousedOn self event shiftDown))
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((and (not (InCabinetRect)) (== (event type?) saidEvent))
						(NeedToGetCloser)
					)
					((not (Btst fCupboardOpen))
						(Print 65 34 #at -1 130 #width 280 #mode teJustCenter)
					)
					((Btst fGotCheese)
						(Print 65 35 #at -1 130 #width 280 #mode teJustCenter)
					)
					(else
						(Print 65 36 #at -1 130 #width 280 #mode teJustCenter)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance poof of Prop
	(properties
		z 26
		view 265
		loop 6
	)
)

(instance stool of View
	(properties
		x 92
		y 94
		view 265
		loop 5
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/stool')
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((and (InBedroom) (>= argc 2))
						(InOtherRoom)
					)
					((and (> (ego distanceTo: stool) 26) (>= argc 2))
						(NeedToGetCloser)
					)
					(else
						(Print 65 37)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance rmCheese of View
	(properties
		x 112
		y 44
		description {cheese}
		view 265
		loop 5
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/cheese')
				(self doVerb: verbLook event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fGotCheese)
						((inventory at: iCheese) showSelf: ego)
					)
					((not (InCabinetRect))
						(NeedToGetCloser)
					)
					((not (Btst fCupboardOpen))
						(Print 65 22)
					)
					((not (Btst fGotCheese))
						(Print 65 36 #at -1 130 #width 280 #mode teJustCenter)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance note of View
	(properties
		x 240
		y 108
		z 10
		view 265
		loop 5
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/note,note')
				(self doVerb: verbLook event)
			)
			((Said 'get,take/note,note')
				(cond 
					((Btst fGotNote)
						(Print 65 21)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((curRoom script?)
						(CantDo)
					)
					((not (ego inRect: 220 98 244 123))
						(CantReach)
					)
					(else
						(curRoom setScript: getNote)
					)
				)
				(event claimed: TRUE)
			)
			(else (super handleEvent: event))
		)
	)
	
	(method (doVerb theVerb event)
		(switch theVerb
			(verbLook
				(cond 
					((and (not (InBedroom)) (>= argc 2))
						(Print 65 38)
					)
					((and (>= argc 2) (>= (ego distanceTo: self) 30))
						(Print 65 39 #at -1 130 #width 280 #mode teJustCenter)
					)
					((>= argc 2)
						(Print 65 40 #at -1 130 #width 280 #mode teJustCenter)
					)
					(else
						(Print 65 41 #at -1 130 #width 280 #mode teJustCenter)
					)
				)
				(if (>= argc 2)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance witchHome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(witch
					illegalBits: 0
					startUpd:
					setMotion: MoveTo 93 105 self
				)
			)
			(1
				(if (or (Btst fInvisible) haloTimer)
					(self changeState: 3)
				else
					(witch setMotion: Chase ego 30 self)
				)
			)
			(2
				(HandsOff)
				(witchEnter start: 1)
				(witch setMotion: 0)
				(self dispose:)
				(witch setScript: witchEnter)
			)
			(3
				(witch setMotion: 0 stopUpd:)
				(poof
					init:
					cycleSpeed: 1
					posn: (witch x?) (witch y?)
					setCycle: EndLoop self
				)
			)
			(4
				(witch hide:)
				(poof cycleSpeed: 2 setCycle: BegLoop self)
			)
			(5
				(poof dispose:)
				(if (Btst fInvisible)
					(Print 65 42)
				else
					(Print 65 43)
				)
				(witch dispose:)
			)
		)
	)
)

(instance getCheese of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(= cycles 4)
			)
			(1
				(rmCheese dispose:)
				(GettingSound init: play:)
				(Print 65 44 #at -1 130 #width 280 #mode teJustCenter)
				(ego get: iCheese)
				(SolvePuzzle fGotCheese 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getNote of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego 239 107)
				(= cycles 4)
			)
			(1
				(note dispose:)
				(GettingSound init: play:)
				(Print 65 45 #at -1 130 #width 280 #mode teJustCenter)
				(ego get: iNote)
				(SolvePuzzle fGotNote 1)
				(= cycles 1)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openCabinet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(= cycles 4)
			)
			(1
				(door setCycle: EndLoop self)
			)
			(2
				(door stopUpd:)
				(HandsOn)
				(Bset fCupboardOpen)
				(SolvePuzzle fFoundCheese 2)
				(self dispose:)
			)
		)
	)
)

(instance closeCabinet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(= cycles 4)
			)
			(1
				(door setCycle: BegLoop self)
			)
			(2
				(door stopUpd:)
				(HandsOn)
				(Bclr fCupboardOpen)
				(self dispose:)
			)
		)
	)
)

(instance witchEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fWitchIsHome)
				((ScriptID 0 21) number: 85 loop: -1 play:)
				(witch
					view: 115
					init:
					illegalBits: cWHITE
					setPri: -1
					setCycle: Walk
					posn: 36 126
					loop: 0
					setMotion: MoveTo 68 122 self
				)
			)
			(1
				(if (& (ego onControl: origin) cLMAGENTA)
					(witch setScript: witchToPot)
				else
					(Face witch ego)
					(witch view: 118 cel: 0 setCycle: EndLoop self)
					((ScriptID 0 21) number: 86 loop: 1 play:)
				)
			)
			(2
				(HandsOff)
				(Print 65 46 #at -1 130 #width 280 #mode teJustCenter)
				(witch
					view: 115
					setCycle: Walk
					setAvoider: Avoider
					setMotion: Chase ego 24
				)
				(= seconds 4)
			)
			(3
				(Face witch ego)
				(Print 65 47 #at -1 130 #width 280 #mode teJustCenter)
				(witch view: 118 cel: 0 setCycle: EndLoop)
				((ScriptID 0 21) number: 86 loop: 1 play:)
				(poof init: posn: (ego x?) (ego y?) setCycle: EndLoop self)
			)
			(4
				(ego hide:)
				(poof setCycle: BegLoop self)
			)
			(5
				(= local1 1)
				((ScriptID 0 21) number: 87 loop: 1 play:)
				(poof posn: 160 57 setCycle: EndLoop self)
			)
			(6
				(NormalEgo)
				(ego posn: 160 57 show:)
				(poof setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(curRoom newRoom: 95)
			)
		)
	)
)

(instance witchToPot of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (& (ego onControl: origin) cLMAGENTA))
				(not (& (ego onControl: origin) cLCYAN))
				(< state 6)
			)
			(= seconds 0)
			(self changeState: 6)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(witch
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 106 96 self
				)
			)
			(1
				(witch setMotion: MoveTo 99 91 self)
				(ego observeBlocks: stoolBlock)
			)
			(2
				(HandsOn)
				(Print 65 52 #at -1 130 #width 280 #mode teJustCenter)
				(witch
					view: 119
					loop: 1
					cel: 0
					illegalBits: 0
					posn: 94 92
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(Print 65 53 #at -1 130 #width 280 #mode teJustCenter)
				(if (CheckHowFast 0)
					(= seconds 9)
				else
					(= seconds 6)
				)
			)
			(4
				(Print 65 54 #at -1 130 #width 280 #mode teJustCenter)
				(if (CheckHowFast 0)
					(= seconds 10)
				else
					(= seconds 7)
				)
			)
			(5
				(Print 65 55 #at -1 130 #width 280 #mode teJustCenter)
				(if (== (curRoom script?) shoveWitch)
					(witch setScript: 0)
				else
					(witch setScript: witchPace)
				)
			)
			(6
				(Print 65 56)
				(HandsOff)
				(if (< (ego x?) 130)
					(ego view: 52 setLoop: 1 setMotion: MoveTo 164 96)
					(= cycles 8)
				else
					(self cue:)
				)
			)
			(7
				(witch view: 118 loop: 4 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(8
				(ego setMotion: 0)
				((ScriptID 0 21) number: 86 loop: 1 play:)
				(witch setCycle: EndLoop self)
			)
			(9
				(witch
					view: 115
					setCycle: Walk
					cycleSpeed: 0
					posn: 101 97
				)
				(witchEnter start: 2)
				(witch setScript: witchEnter)
			)
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_1826
			ret     
			jmp      code_1913
code_1826:
			pushi    1
			lofsa    'pull,kill/witch[/caldron,brew,liquid]'
			push    
			callk    Said,  2
			bnt      code_18ce
			pushi    #inRect
			pushi    4
			pushi    110
			pushi    80
			pushi    124
			pushi    99
			lag      ego
			send     12
			not     
			bnt      code_1852
			pushi    2
			pushi    65
			pushi    48
			calle    Print,  4
			jmp      code_1913
code_1852:
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			bnt      code_1883
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_1871
			ldi      23
			jmp      code_1873
code_1871:
			ldi      16
code_1873:
			eq?     
			bnt      code_1883
			pushi    2
			pushi    65
			pushi    49
			calle    Print,  4
			jmp      code_1913
code_1883:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #number
			pushi    1
			pushi    40
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    21
			callk    ScriptID,  4
			send     10
			pushi    #script
			pushi    0
			lofsa    witch
			send     4
			push    
			lofsa    witchPace
			eq?     
			bnt      code_18b7
			pushi    #setScript
			pushi    1
			pushi    0
			lag      curRoom
			send     6
			jmp      code_1913
code_18b7:
			pushi    #setScript
			pushi    1
			pushi    0
			lofsa    witch
			send     6
			pushi    #setScript
			pushi    1
			lofsa    shoveWitch
			push    
			lag      curRoom
			send     6
			jmp      code_1913
code_18ce:
			pushi    1
			lofsa    'pull,kill/witch'
			push    
			callk    Said,  2
			bnt      code_18e5
			pushi    2
			pushi    65
			pushi    50
			calle    Print,  4
			jmp      code_1913
code_18e5:
			pushi    1
			lofsa    'shoot/witch[<shot]'
			push    
			callk    Said,  2
			bt       code_18fb
			pushi    1
			lofsa    'use/shot,(shot<shot)'
			push    
			callk    Said,  2
			bnt      code_1907
code_18fb:
			pushi    2
			pushi    65
			pushi    51
			calle    Print,  4
			jmp      code_1913
code_1907:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Script,  6
			bnt      code_1913
			ret     
code_1913:
			ret     
		)
	)
)

(instance witchPace of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= (ego onControl: origin) cLMAGENTA)
				(< 1 state)
				(< state 7)
			)
			(= seconds 0)
			(self changeState: 7)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego ignoreBlocks: stoolBlock)
				(witch setCycle: BegLoop self)
			)
			(1
				(witch
					view: 115
					loop: 2
					setCycle: Walk
					cycleSpeed: 0
					posn: 99 91
					setMotion: MoveTo 104 96 self
				)
			)
			(2
				(witch setMotion: MoveTo 50 124 self)
			)
			(3 (= seconds 2))
			(4
				(witch setMotion: MoveTo 106 100 self)
			)
			(5 (= seconds 2))
			(6 (self changeState: 2))
			(7
				(HandsOff)
				(Print 65 56)
				(ego setMotion: 0)
				(witchEnter start: 1)
				(witch setScript: witchEnter)
			)
		)
	)
)

(instance smokeFace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smoke loop: 2 setCycle: Forward)
				(= cycles (Random 50 100))
			)
			(1
				(smoke loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance seeSpectre of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego fire)
				(= cycles 4)
			)
			(1
				(ego stopUpd:)
				(smoke loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2
				(smoke loop: 2 setCycle: Forward)
				(= seconds 2)
			)
			(3
				(if (not (>= howFast 1)) (smoke stopUpd:))
				(Print 65 57 #at -1 130 #width 280 #mode teJustCenter)
				(HandsOn)
				(ego startUpd:)
				(if (>= howFast 1) (smoke setScript: smokeFace))
				(self dispose:)
			)
		)
	)
)

(instance shoveWitch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fPushingWitch)
				(ego
					ignoreActors:
					illegalBits: 0
					view: 65
					setMotion: 0
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				((ScriptID 0 21) number: 40 loop: 1 play:)
				(witch
					view: 119
					ignoreActors:
					ignoreControl:
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(witch dispose:)
				(goop init:)
				(ego setCycle: BegLoop)
				(= cycles 12)
			)
			(2
				(Print 65 61)
				(SolvePuzzle fKilledWitch 7)
				(HandsOn)
				(NormalEgo)
				(ego loop: 1)
				(self dispose:)
			)
		)
	)
)

(instance goop of View
	(properties
		x 94
		y 92
		view 119
		cel 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (and argc (InBedroom))
					(InOtherRoom)
				else
					(Print 65 62)
				)
			)
		)
	)
)

(instance bars of NewFeature
	(properties
		x 169
		y 44
		nsTop 11
		nsLeft 141
		nsBottom 78
		nsRight 197
		description {bars}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check[<at]/bar,cage')
				(self doLook: TRUE)
			)
		)
	)
	
	(method (doLook)
		(if (and argc (InBedroom))
			(InOtherRoom)
		else
			(Print 65 63)
		)
	)
)

(instance bed of NewFeature
	(properties
		x 281
		y 99
		noun '/bed'
		nsTop 77
		nsLeft 265
		nsBottom 122
		nsRight 298
		description {bed}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(<= nsLeft (event x?))
				(<= (event x?) nsRight)
				(<= nsTop (event y?))
				(<= (event y?) nsBottom)
				(& (event modifiers?) shiftDown)
			)
			(Print 65 64)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doLook)
		(if (not (InBedroom))
			(InOtherRoom)
		else
			(Print 65 64)
		)
	)
)

(instance bed2 of NewFeature
	(properties
		x 236
		y 130
		noun '/bed'
		nsTop 115
		nsLeft 211
		nsBottom 145
		nsRight 262
		description {bed}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(<= nsLeft (event x?))
				(<= (event x?) nsRight)
				(<= nsTop (event y?))
				(<= (event y?) nsBottom)
				(& (event modifiers?) shiftDown)
			)
			(Print 65 64)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doLook)
		(if (not (InBedroom))
			(InOtherRoom)
		else
			(Print 65 64)
		)
	)
)

(instance bedroomTable of NewFeature
	(properties
		x 246
		y 97
		nsTop 93
		nsLeft 230
		nsBottom 102
		nsRight 262
		description {bedroomTable}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/endtable,endtable')
				(self doLook: TRUE)
			)
		)
	)
	
	(method (doLook)
		(if (and argc (not (InBedroom)))
			(InOtherRoom)
		else
			(Print 65 65 #at -1 130 #width 280 #mode teJustCenter)
			(if (not (Btst fGotNote))
				(Print 65 66 #at -1 130 #width 280 #mode teJustCenter)
			)
		)
	)
)

(instance table of NewFeature
	(properties
		x 117
		y 114
		noun '/table<kitchen'
		nsTop 102
		nsLeft 107
		nsBottom 127
		nsRight 128
		description {table}
		sightAngle 360
		getableDist 320
		seeableDist 520
		shiftClick 369
		contClick 371
		lookStr {That is the table where the witch eats her food, whatever... or WHOever...that is!}
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/table<kitchen')
				(self doLook: TRUE)
			)
		)
	)
	
	(method (doLook)
		(if (and argc (InBedroom))
			(InOtherRoom)
		else
			(Print 65 67)
		)
	)
)

(instance spiderWeb of Feature
	(properties
		x 226
		y 31
		nsTop 18
		nsLeft 212
		nsBottom 44
		nsRight 240
		description {spider web}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 320
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/cobweb,spiderweb,(spider<cobweb)')
				(self doVerb: verbLook verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (and (not (InBedroom)) (>= argc 2))
					(Print 65 68)
				else
					(Print 65 69)
				)
			)
		)
	)
)

(instance pot of NewFeature
	(properties
		x 63
		y 75
		noun '/caldron,caldron'
		nsTop 67
		nsLeft 43
		nsBottom 84
		nsRight 84
		description {pot}
		sightAngle 360
		seeableDist 520
		shiftClick 369
		lookStr {The pot is hot enough to burn your hand.__The fire must have been burning for a long time.}
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((MousedOn self event shiftDown)
				(Print lookStr #at -1 130 #width 280 #mode teJustCenter)
				(event claimed: TRUE)
			)
			((Said 'open,open/caldron')
				(cond 
					((InBedroom)
						(InOtherRoom)
					)
					((ego inRect: 15 70 110 110)
						(Print 65 70)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'look,check,feel/caldron')
				(cond 
					((InBedroom)
						(InOtherRoom)
					)
					((ego inRect: 15 70 110 110)
						(Print 65 23 #at -1 130 #width 280 #mode teJustCenter)
					)
					(else
						(CantReach)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance GettingSound of Sound
	(properties
		number 105
		priority 10
	)
)
