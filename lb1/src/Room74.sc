;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room74 0
)
(synonyms
	(stair upstair)
	(armoire armoire closet)
	(fifi person girl)
	(decanter bottle)
)

(local
	local0
	local1
	local2
	local3
	local4
	theCSound
	local6
	local7
	local8
	local9
	local10
)
(procedure (localproc_000c)
	(if local7
		(= local7 0)
		(= currentPalette 0)
		(DrawPic 174 dpOPEN_LEFT TRUE 0)
		(DrawPic 74 dpOPEN_LEFT FALSE 0)
		(if (and (>= currentAct 2) (< currentAct 5))
			(addToPics add: glasses)
			(Room74 setFeatures: glasses)
		)
		(if (>= currentAct 2)
			(addToPics add: decanter)
			(Room74 setFeatures: decanter)
		)
		(addToPics
			add: wardrobe sofa table1 mirror chair1 chair2 table2 phono bed photo
			eachElementDo: #init
			doit:
		)
		(Room74
			setFeatures:
				sofa
				table1
				mirror
				wardrobe
				chair1
				chair2
				table2
				bed
				photo
				phono
		)
		(cast eachElementDo: #show)
	)
)

(procedure (localproc_00ed)
	(if (not local7)
		(= local7 1)
		(= currentPalette 1)
		(DrawPic 174 dpOPEN_RIGHT TRUE 1)
		(addToPics dispose:)
		(features dispose:)
		(cast eachElementDo: #hide)
		(DrawPic 74 dpOPEN_RIGHT FALSE 0)
		(door show:)
		(lamp2 show:)
		(ego show:)
	)
)

(instance Room74 of Rm
	(properties
		picture 74
		curPic 74
	)
	
	(method (init)
		(= south 47)
		(= west 76)
		(= local8 (DoSound sndVOLUME))
		(Load rsPIC 174)
		(Load rsPIC 74)
		(super init:)
		(= overlays 174)
		(cond 
			((== prevRoomNum 47) (ego setPri: -1 posn: 42 187))
			((!= prevRoomNum 64) (ego posn: 6 98))
			(else (= local8 (DoSound sndVOLUME)))
		)
		(door loop: (if (> prevRoomNum 74) 3 else 6))
		(if (and (>= currentAct 2) (< currentAct 5))
			(addToPics add: glasses)
			(self setFeatures: glasses)
		)
		(if (>= currentAct 2)
			(if (< currentAct 6)
				(addToPics add: decanter)
				(self setFeatures: decanter)
			)
			(if (and (& global123 $0040) (not (ego has: 5)))
				(door cel: (door lastCel:) ignoreActors: 1)
				(= global117 (| global117 $8000))
			)
		)
		(Load rsVIEW 56)
		(LoadMany 132 43 48)
		(if howFast
			(lamp1 setPri: 11 setCycle: Fwd ignoreActors: 1 init:)
			(lamp2 setPri: 13 setCycle: Fwd ignoreActors: 1 init:)
		else
			(lamp1 setPri: 11 ignoreActors: 1 init: stopUpd:)
			(lamp2 setPri: 13 ignoreActors: 1 init: stopUpd:)
		)
		(if (& global117 $8000) (door loop: 6 cel: 3))
		(door setPri: 7 ignoreActors: 1 init: stopUpd:)
		(Tswing setPri: 6 ignoreActors: 1 init: stopUpd:)
		(Bswing setPri: 8 ignoreActors: 1 init: stopUpd:)
		(= gDoor record)
		(switch currentAct
			(1
				(if (== global126 1) (self setRegions: 244))
			)
			(2 (self setRegions: 261))
			(4
				(= local4 1)
				(Load rsSOUND 223)
				(= theCSound cSound)
				(LoadMany 143 243 371)
				(LoadMany 128 474 928)
				(= global208 16)
				(= [global377 4] 371)
				(if (or (== prevRoomNum 64) (>= global192 64))
					(if (== global192 64)
						(Load rsVIEW 471)
						(Fifi view: 471 posn: 271 104 loop: 1 init:)
						(self setScript: finishDress)
					else
						(Fifi
							view: 474
							setPri: 13
							illegalBits: 0
							posn: 242 128
							loop: 0
							cycleSpeed: 2
							init:
						)
						(FifiButt setPri: 14 init: stopUpd:)
						(= local10 1)
						(self setScript: primp)
					)
				else
					(Fifi
						view: 462
						loop: 0
						cel: 0
						illegalBits: 0
						posn: 118 76
						init:
						hide:
					)
				)
			)
			(5
				(if
					(and
						(not (& global123 $0010))
						(not (& global123 $0040))
					)
					(= local4 1)
					(self setRegions: 273)
					(flowers init: stopUpd:)
				)
			)
		)
		(if (== prevRoomNum 64)
			(= local7 1)
			(localproc_000c)
		else
			(localproc_00ed)
		)
		(if (not (& global117 $8000))
			(ego illegalBits: -32760)
		else
			(ego illegalBits: -32768)
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(if (== prevRoomNum 47) (Print 74 0) else (Print 74 1))
		)
		(super doit:)
		(if
			(or
				(and (& (ego onControl: 0) $0008) (& global123 $0040))
				(& (ego onControl: 0) $0002)
			)
			(Bclr 46)
			(curRoom newRoom: 75)
		)
		(cond 
			((ego inRect: -10 0 58 100) (= local2 3))
			((ego inRect: 0 100 64 200)
				(if (!= local2 1)
					(= local2 1)
					(localproc_00ed)
					(DoSound sndVOLUME 0)
					(Room74 picture: 74)
				)
			)
			(else
				(= local2 2)
				(DoSound sndVOLUME local8)
				(localproc_000c)
				(if (not global100) (= global100 1) (Print 74 2))
				(if
				(and (== currentAct 4) (< global192 64) (not local6))
					(++ local6)
					(HandsOff)
					(Bset 20)
					(self setScript: startDress)
				)
			)
		)
		(if
		(and (& (ego onControl: 1) $0010) (== local0 0))
			(= local0 1)
			(Tswing cel: 1 forceUpd:)
			(Bswing cel: 1 forceUpd:)
		)
		(if (& (ego onControl: 1) $4000)
			(= local3 1)
			(ego setPri: 5)
		else
			(= local3 0)
			(ego setPri: -1)
		)
		(if
		(and (& (ego onControl: 1) $0020) (== local0 0))
			(= local0 1)
			(Tswing cel: 3 forceUpd:)
			(Bswing cel: 3 forceUpd:)
		)
		(if
			(and
				(or
					(& (ego onControl: 1) $0001)
					(& (ego onControl: 1) $4000)
				)
				(== local0 1)
			)
			(Tswing setCycle: End)
			(Bswing setCycle: End)
			(= local0 0)
		)
		(cond 
			((< (ego x?) 58) (= vertAngle 0))
			((< (ego x?) 180) (= vertAngle 157))
			(else (= vertAngle 140))
		)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(DisposeScript 983)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(DisposeScript 990)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(if
					(and
						(> (ego x?) 64)
						global208
						(Said
							'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
						)
					)
					(self setScript: (ScriptID 243 0))
					((self script?) handleEvent: event)
					(if (event claimed?) (return 1))
				)
				(if (Said 'examine>')
					(cond 
						((Said '/room<dressing') (if (and (not local7) 2) (Print 74 3) else (DontSee)))
						((Said '/bedroom') (if 2 (Print 74 2) else (Print 74 4)))
						((Said '[<around,at][/room]')
							(cond 
								(local3 (Print 74 5))
								((== local2 1) (Print 74 0))
								((== local2 3) (Print 74 1))
								(
								(and (not (& global123 $0010)) (== currentAct 5)) (= global123 (| global123 $0010)) (Print 74 6))
								(else (Print 74 2))
							)
						)
						((or (Said '<down') (Said '/downstair'))
							(cond 
								((== local2 1) (Print 74 7))
								(local3 (Print 74 5))
								(local4 (Print 74 8))
								(else (event claimed: 0))
							)
						)
						((or (Said '<up') (Said '/stair')) (if (== local2 1) (Print 74 0) else (event claimed: 0)))
						((Said '/dormer') (if (== local2 2) (Print 74 9) else (event claimed: 0)))
						((Said '/attic') (if (!= local2 3) (Print 74 10) else (Print 74 11)))
						((Said '<below/carpet')
							(if (== local2 2)
								(Print 74 12)
							else
								(event claimed: 0)
							)
						)
						((Said '/window')
							(if (== local2 2)
								(Print 74 13)
							else
								(event claimed: 0)
							)
						)
						((Said '/curtain')
							(if (== local2 2)
								(Print 74 14)
							else
								(event claimed: 0)
							)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (and (== theCSound cSound) (!= n 64))
			(theCSound stop:)
		)
		(Wait 1)
		(Wait 10)
		(= saveDisabled 0)
		(DoSound sndVOLUME local8)
		(super newRoom: n)
	)
)

(instance startDress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(record setCycle: Fwd init:)
				(if (!= prevRoomNum 64)
					(theCSound number: 223 loop: -1 play:)
				)
				(Fifi show: view: 462 loop: 1 cel: 0 setCycle: End self)
			)
			(1
				(Fifi loop: 2 cel: 0 setCycle: End self)
			)
			(2
				(Fifi
					view: 460
					setCycle: Walk
					setMotion: MoveTo 227 107 self
				)
			)
			(3
				(Tswing cel: 1 forceUpd:)
				(Bswing cel: 1 forceUpd:)
				(Fifi setMotion: MoveTo 241 107 self)
			)
			(4
				(Tswing cel: 0 forceUpd:)
				(Bswing cel: 0 forceUpd:)
				(Fifi setMotion: MoveTo 271 107 self)
			)
			(5 (curRoom newRoom: 64))
		)
	)
)

(instance finishDress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(record setCycle: Fwd init:)
				(= global192 74)
				(Fifi setCycle: Walk setMotion: MoveTo 245 107 self)
			)
			(1
				(Tswing cel: 3 forceUpd:)
				(Bswing cel: 3 forceUpd:)
				(Fifi setMotion: MoveTo 225 110 self)
			)
			(2
				(Tswing cel: 0 forceUpd:)
				(Bswing cel: 0 forceUpd:)
				(Fifi setMotion: MoveTo 175 110 self)
			)
			(3
				(Fifi setMotion: MoveTo 181 151 self)
			)
			(4
				(Fifi illegalBits: 0 setMotion: MoveTo 250 156 self)
			)
			(5
				(Fifi setScript: primp)
				(client setScript: 0)
			)
		)
	)
)

(instance primp of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (!= prevRoomNum 64) local10)
			(if (> (ego x?) 65)
				(= local10 0)
				(theCSound number: 223 loop: -1 play:)
				(record setCycle: Fwd init:)
			else
				(theCSound stop:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(Fifi
					view: 474
					setPri: 13
					illegalBits: 0
					posn: 242 128
					loop: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(FifiButt setPri: 14 init: stopUpd:)
			)
			(1
				(Fifi loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(Fifi loop: 2 cel: 0 setCycle: End self)
			)
			(3
				(Fifi loop: 3 setCycle: Fwd)
				(= seconds 3)
			)
			(4
				(Fifi loop: 0 cel: 1 setCycle: Beg self)
			)
			(5 (= seconds 8) (= state 12))
			(6
				(Fifi loop: 4 cel: 0 setCycle: End self)
			)
			(7
				(Fifi loop: 5 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(8
				(Fifi loop: 4 cel: 1 setCycle: Beg self)
			)
			(9 (= seconds 8) (= state 12))
			(10
				(Fifi loop: 6 cel: 0 setCycle: End self)
			)
			(11
				(Fifi loop: 7 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(12
				(Fifi loop: 6 cel: 1 setCycle: Beg self)
			)
			(13
				(if local9
					(switch (Random 1 3)
						(1 (= state -1))
						(2 (= state 5))
						(3 (= state 9))
					)
				)
				(= seconds 6)
			)
			(14
				(++ local9)
				(Fifi view: 478 loop: 0 cel: 0 setCycle: End self)
			)
			(15
				(Fifi loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(16
				(Fifi loop: 2 cel: 0 setCycle: End self)
			)
			(17
				(Fifi loop: 3 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(18
				(Fifi loop: 2 cel: 3 setCycle: Beg self)
				(= state -1)
			)
		)
	)
)

(instance knockDoor of Script
	(properties)
	
	(method (doit)
		(if (== (myMusic prevSignal?) 11)
			(DoSound sndVOLUME 0)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMusic prevSignal: 0)
				(HandsOff)
				(ego view: 56 loop: 0 setCycle: End self)
			)
			(1
				(myMusic number: 48 loop: 1 priority: 15 play:)
				(DoSound sndVOLUME local8)
				(ego loop: 2 setCycle: Fwd)
			)
			(2
				(Print 74 15)
				(ego view: 56 loop: 0 cel: 3 setCycle: Beg self)
			)
			(3
				(HandsOn)
				(ego view: 0 setCycle: Walk loop: 3)
				(client setScript: 0)
			)
		)
	)
)

(instance wardrobe of RPicView
	(properties
		y 78
		x 84
		view 174
		loop 2
		cel 2
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/armoire') (Print 74 16))
			(
			(or (MousedOn self event 3) (Said 'examine/armoire')) (event claimed: 1) (Print 74 17))
		)
	)
)

(instance sofa of RPicView
	(properties
		y 78
		x 138
		view 174
		loop 2
		cel 1
		priority 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {couch})
		)
	)
)

(instance table1 of RPicView
	(properties
		y 79
		x 192
		view 174
		loop 2
		cel 7
		priority 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 74 18)
		)
	)
)

(instance bed of RPicView
	(properties
		y 142
		x 232
		view 174
		loop 2
		priority 10
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/bed') (Print 74 19))
			((MousedOn self event 3) (event claimed: 1) (ParseName {bed}))
		)
	)
)

(instance mirror of RPicView
	(properties
		y 158
		x 268
		view 174
		loop 2
		cel 3
		priority 12
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'examine<in/mirror')
					(Said 'examine/reflection')
				)
				(if (< (ego distanceTo: mirror) 80)
					(= theTalker 12)
					(Say 0 74 20)
				else
					(NotClose)
				)
			)
			((Said 'examine<(behind,below)/mirror') (Print 74 21))
			((Said 'examine/mirror') (Print 74 22))
			((Said 'get/mirror') (Print 74 23))
			(
				(Said
					'open,(examine<in)/vanity,drawer,(nightstand<dressing)'
				)
				(if (== local2 2)
					(Print 74 24)
				else
					(event claimed: 0)
				)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/vanity,(nightstand<dressing)')
				)
				(if (== local2 2)
					(Print 74 25)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
			)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 157
		x 245
		view 174
		loop 2
		cel 8
		priority 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 157
		x 101
		view 174
		loop 2
		cel 5
		priority 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance table2 of RPicView
	(properties
		y 157
		x 140
		view 174
		loop 2
		cel 7
		priority 11
	)
	
	(method (handleEvent event)
		(if
			(and
				(>= currentAct 2)
				(< currentAct 6)
				(or (MousedOn self event 3) (Said 'examine/nightstand'))
			)
			(if (== currentAct 5)
				(if local4 (Print 74 26) else (Print 74 27))
			else
				(Print 74 28)
			)
			(event claimed: 1)
		)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {table})
		)
	)
)

(instance glasses of RPicView
	(properties
		y 141
		x 142
		view 174
		loop 2
		cel 4
		priority 14
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/glass') (Print 74 29))
			((Said 'examine<in/glass') (Print 74 30))
			(
			(or (Said 'examine/glass') (MousedOn self event 3)) (event claimed: 1) (Print 74 28))
		)
	)
)

(instance decanter of RPicView
	(properties
		y 141
		x 130
		view 174
		loop 2
		cel 9
		priority 14
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'examine<use<monocle/decanter')
					(Said 'examine,(examine<at)/decanter/monocle<with')
					(Said 'examine/fingerprint')
				)
				(if (ego has: 1)
					(if (< (ego distanceTo: table2) 20)
						(if (< currentAct 5)
							(Print 74 31 #icon 635 0 0)
						else
							(Print 74 32 #icon 635 0 2)
						)
						(Bset 11)
						(Bset 30)
					else
						(NotClose)
					)
				else
					(DontHave)
				)
			)
			(
				(or
					(Said 'drink/[<alcohol]')
					(Said 'drink,pour,get/decanter,alcohol')
					(Said 'open,get/decanter,alcohol')
				)
				(if (and local4 (== currentAct 5))
					(Print 74 33)
				else
					(Print 74 29)
				)
			)
			((Said 'examine<in/decanter,alcohol') (if (>= currentAct 5) (Print 74 34) else (Print 74 35)))
			(
				(or
					(Said 'examine/decanter,alcohol')
					(MousedOn self event 3)
				)
				(Bset 30)
				(if (>= currentAct 5) (Print 74 34) else (Print 74 36))
				(event claimed: 1)
			)
		)
	)
)

(instance photo of RPicView
	(properties
		y 40
		x 138
		view 174
		loop 2
		cel 6
		priority 4
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {picture})
		)
	)
)

(instance phono of RPicView
	(properties
		y 62
		x 191
		view 174
		loop 2
		cel 11
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'cease,(rotate<off)/record,gramophone')
				(if (< (ego distanceTo: phono) 20)
					(if (and local4 (== currentAct 5))
						(if (record cycler?)
							(record setCycle: 0)
							(gDoor_2 stop:)
							(Print 74 37)
						else
							(Print 74 38)
						)
					else
						(Print 74 39)
					)
				else
					(NotClose)
				)
			)
			(
				(or
					(Said
						'wind,play,(rotate<on)/gramophone,music,record,(player<record)'
					)
					(Said 'get/music,record,(player<record)')
				)
				(if (and local4 (== currentAct 5))
					(Print 74 40)
				else
					(Print 74 39)
				)
			)
			((Said 'examine,find/record')
				(cond 
					((ego has: 9) (event claimed: 0))
					(
					(and local4 (== currentAct 5) (record cycler?)) (Print 74 41))
					((record cycler?) (Print 74 42))
					(else (Print 74 43))
				)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/gramophone,armoire,(player<record)')
				)
				(event claimed: 1)
				(cond 
					(
					(and local4 (== currentAct 5) (record cycler?)) (Print 74 41))
					((>= currentAct 5) (Print 74 44))
					(else (Print 74 45))
				)
			)
		)
	)
)

(instance record of Prop
	(properties
		y 53
		x 193
		view 174
		loop 7
		cel 5
		priority 4
		signal $0010
	)
)

(instance flowers of Prop
	(properties
		y 143
		x 141
		view 174
		loop 2
		cel 10
		priority 14
		signal $4010
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/blossom') (Print 74 46))
			(
			(or (MousedOn self event 3) (Said 'examine/blossom')) (event claimed: 1) (Print 74 47))
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 93
		x 80
		view 174
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'change,(attach<on),wear/cloth') (Print 74 48))
			((Said 'get,get,move/cloth') (Print 74 49))
			(
			(Said '(examine<(below,through)),search/dirt,cloth') (if local3 (Print 74 50) else (NotClose)))
			((Said 'examine/dirt,cloth,pile')
				(cond 
					(local3 (Print 74 51))
					(local4 (Print 74 8))
					(else (event claimed: 0))
				)
			)
			((and local3 (Said 'drag,close,open/curtain')) (Print 74 52))
			((MousedOn self event 3) (event claimed: 1) (ParseName {lamp}))
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 92
		x 68
		view 174
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/lamp') (Print 74 53))
			((MousedOn self event 3) (event claimed: 1) (ParseName {lamp}))
		)
	)
)

(instance door of Prop
	(properties
		y 104
		x 48
		view 174
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open/door')
				(cond 
					((ego inRect: 21 95 47 110)
						(cond 
							(global117
								(if (& global117 $8000)
									(AlreadyOpen)
								else
									(if (< (ego y?) (door y?)) (ego posn: 33 89))
									(self setScript: myDoor)
								)
							)
							((== local2 1) (Print 74 54))
							(else (Print 74 55))
						)
					)
					((== local2 2) (event claimed: 0))
					(else (NotClose))
				)
			)
			((Said 'close/door')
				(cond 
					((ego inRect: 21 95 47 115)
						(if (not (& global117 $8000))
							(AlreadyClosed)
						else
							(self setScript: myDoor)
						)
					)
					((== local2 2) (event claimed: 0))
					(else (NotClose))
				)
			)
			(
			(or (Said 'use,unbar/key,door') (Said 'open/door/key'))
				(cond 
					(global117 (Print 74 56))
					((ego inRect: 21 95 47 110)
						(cond 
							((== local2 3) (= global117 1) (Print 74 57))
							((ego has: 5) (= global117 1) (Print 74 58))
							(else (Print 74 59))
						)
					)
					(else (NotClose))
				)
			)
			((Said 'bang[/door]')
				(if (ego inRect: 21 95 47 110)
					(if (not (& global117 $8000))
						(self setScript: knockDoor)
					else
						(Print 74 60)
					)
				else
					(NotClose)
				)
			)
			((Said 'find,enter,(go[<in])/attic')
				(if (== local2 1)
					(Print 74 61)
				else
					(event claimed: 0)
				)
			)
			((Said 'examine/keyhole')
				(if (== local2 1)
					(Print 74 62)
				else
					(event claimed: 0)
				)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/door[<attic]')
				)
				(event claimed: 1)
				(if (== local2 2) (Print 74 63) else (Print 74 64))
			)
		)
	)
)

(instance Tswing of Prop
	(properties
		y 94
		x 233
		view 174
		loop 4
	)
	
	(method (handleEvent event)
		(if
			(and
				(== local2 2)
				(or
					(MousedOn self event 3)
					(Said 'examine/door[<swinging]')
				)
			)
			(Print 74 65)
			(event claimed: 1)
		)
	)
)

(instance Bswing of Prop
	(properties
		y 105
		x 245
		view 174
		loop 5
	)
	
	(method (handleEvent event)
		(if
			(and
				(== local2 2)
				(or
					(MousedOn self event 3)
					(Said 'examine/door[<swinging]')
				)
			)
			(Print 74 65)
			(event claimed: 1)
		)
	)
)

(instance FifiButt of Prop
	(properties
		y 153
		x 252
		view 478
		loop 4
	)
)

(instance Fifi of Act
	(properties)
	
	(method (handleEvent event)
		(if (not local7)
			(= theTalker 5)
			(cond 
				((Said 'hear/fifi') (Print 74 66))
				((Said 'converse/fifi')
					(switch local1
						(0 (Say 1 74 67))
						(1 (Say 1 74 68))
						(2 (Say 1 74 69))
						(3 (Say 1 74 70))
						(4 (Say 1 74 71))
						(else  (Print 74 72))
					)
					(++ local1)
				)
				(
				(or (MousedOn self event 3) (Said 'examine/fifi'))
					(event claimed: 1)
					(if (not (& global207 $0010))
						(= global207 (| global207 $0010))
						(Say 0 74 73)
					else
						(Print 74 74)
					)
				)
			)
		)
	)
)

(instance mySound of Sound
	(properties)
)

(instance myMusic of Sound
	(properties)
)

(instance myDoor of Script
	(properties)
	
	(method (doit)
		(if (== (mySound prevSignal?) 11)
			(DoSound sndVOLUME 0)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (<= (ego y?) (door y?))
					(ego setMotion: MoveTo 33 89 self)
				else
					(ego setMotion: MoveTo 33 106 self)
				)
			)
			(1
				(if (<= (ego y?) (door y?))
					(ego loop: 2)
				else
					(ego loop: 3)
				)
				(mySound prevSignal: 0)
				(HandsOff)
				(if (not (& global117 $8000))
					(mySound number: 43 loop: 1 priority: 10 play:)
					(DoSound sndVOLUME local8)
					(door loop: 6 setCycle: End)
					(ego illegalBits: -32768)
					(= global117 (| global117 $8000))
				else
					(mySound number: 44 loop: 1 priority: 10 play:)
					(DoSound sndVOLUME local8)
					(door loop: 6 setCycle: Beg)
					(ego illegalBits: -32760)
					(= global117 (& global117 $7fff))
				)
			)
			(2
				(if (not (& global117 $8000))
					(door loop: (if (< (ego y?) 104) 3 else 6) stopUpd:)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
