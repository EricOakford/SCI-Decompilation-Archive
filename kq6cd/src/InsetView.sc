;;; Sierra Script 1.0 - (do not remove this comment)
(script# 781)
(include sci.sh)
(use Main)
(use CastleRoom)
(use KQ6Print)
(use Kq6Procs)
(use Inset)
(use Conv)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm781 0
)

(local
	local0
	local1
)
(instance rm781 of CastleRoom
	(properties
		noun 3
		picture 780
		style $000a
		vanishingX 183
		vanishingY 98
	)
	
	(method (init)
		(LoadMany 128 787 785 786 724 726)
		(ego init: setPri: 13 setScale: Scaler 118 70 190 140)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-10
						-10
						329
						-10
						329
						179
						319
						179
						271
						154
						250
						154
						233
						143
						207
						143
						201
						147
						176
						147
						173
						142
						167
						142
						162
						147
						76
						147
						48
						162
						33
						162
						0
						177
						-10
						177
					yourself:
				)
		)
		(features
			add: trunk box rug chandelier
			eachElementDo: #init
		)
		(door
			cel: (* (== prevRoomNum 850) 4)
			init:
			stopUpd:
			approachVerbs: 5
		)
		(super init: &rest)
		(trunkLid init:)
		(boxLid init:)
		(candles init:)
		(doorFrame1ATP addToPic:)
		(doorFrame2ATP addToPic:)
		(fireplaceATP addToPic:)
		(fireplace setCycle: Fwd init:)
		(wardrobeDoor init:)
		(bedATP addToPic:)
		(switch prevRoomNum
			(810
				(wardrobeDoor hide:)
				(self setScript: wardrobeEntrance)
			)
			(else 
				(self setScript: hallDoorEntrance)
			)
		)
		(if (ego scaler?) ((ego scaler?) doit:))
		(if (not script) (theGame handsOn:))
		(theMusic fadeTo: 150 -1)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000) (curRoom newRoom: 850))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(ego setPri: -1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (ego has: 20)
					(messager say: noun theVerb 5)
					1
				else
					(messager say: noun theVerb 4)
					1
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (warnUser param1)
		(switch param1
			(2
				(if inset
					(script next: they_reBack)
					(inset dispose:)
				else
					(self setScript: they_reBack)
				)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance hallDoorEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door cel: 4 forceUpd:)
				(ego
					setPri: -1
					posn: 306 157
					setMotion: MoveTo (door approachX?) (door approachY?) self
				)
			)
			(1
				(ego setPri: 13)
				(door setCycle: Beg self)
			)
			(2
				(soundFx2 number: 902 loop: 1 play:)
				(door stopUpd:)
				(if ((ScriptID 80 0) tstFlag: 709 1)
					((ScriptID 80 0) clrFlag: 709 1)
					(= cycles 10)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(3 (curRoom warnUser: 2))
		)
	)
)

(instance openTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(not ((ScriptID 80 0) tstFlag: 711 4096))
						(OneOf register 64 35)
					)
					(theGame givePoints: 1)
				)
				(ego
					normal: 0
					view: 787
					loop: 0
					cel: 0
					cycleSpeed: 8
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 151 154
					setCycle: CT 3 1 self
				)
			)
			(1
				(if ((ScriptID 80 0) tstFlag: 711 4096)
					(if (OneOf register 64 35)
						(messager say: (= state 4) 35 14 0 self)
					else
						(messager say: 4 5 14 0 self)
					)
				else
					(if (not (OneOf register 64 35))
						(= state 4)
					else
						(soundFx2 number: 781 loop: 1 play:)
					)
					(messager say: 4 register 13 0 self)
				)
			)
			(2
				((ScriptID 80 0) setFlag: 711 4096)
				(trunkLid hide:)
				(soundFx2 number: 904 loop: 1 play:)
				(ego cel: 4 setCycle: End self)
			)
			(3
				(soundFx2 stop:)
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5)
				(theIconBar disable: 0)
				(chestInset init: self curRoom)
			)
			(4
				(soundFx2 number: 905 loop: 1 play:)
				(ego setCycle: CT 3 -1 self)
			)
			(5
				(trunkLid show:)
				(soundFx2 stop:)
				(ego setCycle: Beg self)
			)
			(6
				(theGame handsOn:)
				(ego reset: 1 setPri: 13 posn: 171 148)
				(self dispose:)
			)
		)
	)
)

(instance openEbonyBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 14 5 0 0 self)
			)
			(1
				(ego
					normal: 0
					view: 787
					loop: 2
					cel: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					cycleSpeed: 8
					setCycle: CT 4 1 self
				)
			)
			(2
				(theIconBar disable: 0)
				(ebonyBoxInset init: self curRoom)
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5)
			)
			(3
				(theGame handsOff:)
				(ego setCycle: End self)
			)
			(4
				(ego
					reset: 6
					setPri: 13
					posn: (box approachX?) (box approachY?)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wardrobeEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGlobalSound number: 783 loop: 1 play:)
				(ego
					normal: 0
					view: 785
					loop: 2
					cel: 9
					posn: 58 162
					cycleSpeed: 8
					setScale: 0
					scaleX: 128
					scaleY: 128
					setCycle: Beg self
				)
				(soundFx2 number: 901 loop: 1 play:)
			)
			(1
				(ego loop: 1 cel: 5 setCycle: CT 2 -1 self)
			)
			(2
				(ego setCycle: Beg)
				(soundFx2 number: 902 loop: 1 play:)
				(= ticks 60)
			)
			(3
				(ego
					posn: (wardrobeDoor approachX?) (wardrobeDoor approachY?)
					reset: 0
					setPri: 13
				)
				(wardrobeDoor show: stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance they_reBack of Script
	(properties
		name "they'reBack"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 0 3 1 self)
			)
			(1 (Face ego door self))
			(2
				(soundFx2 number: 901 loop: 1 play:)
				(door setCycle: CT 2 1 self)
			)
			(3
				(soundFx2 stop:)
				(messager say: 1 0 3 2 self)
			)
			(4
				(soundFx2 number: 901 loop: 1 play:)
				(door setCycle: End self)
			)
			(5
				(soundFx2 stop:)
				((ScriptID 80 5) init: posn: 307 158 loop: 1)
				((ScriptID 80 0) setupGuards:)
				((ScriptID 80 5) setMotion: MoveTo 283 158 self)
			)
			(6
				(theMusic number: 710 loop: -1 play:)
				(messager say: 1 0 3 3 self oneOnly: 0)
			)
			(7
				(theMusic fade:)
				((ScriptID 80 0) setFlag: 709 8192)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance openWardrobe of Script
	(properties)
	
	(method (changeState newState)
		(if (not local1)
			(switch (= state newState)
				(0
					(theGame handsOff:)
					(wardrobeDoor hide:)
					(ego
						normal: 0
						view: 785
						setScale: 0
						scaleX: 128
						scaleY: 128
						loop: 1
						cel: 0
						posn: 58 162
						cycleSpeed: 8
						setCycle: CT 2 1 self
					)
				)
				(1
					(theGlobalSound number: 901 loop: 1 play:)
					(ego setCycle: End self)
				)
				(2
					(if (!= prevRoomNum 810)
						(messager say: 12 5 8 0 self)
					else
						(= state (+ state 2))
						(= cycles 1)
					)
				)
				(3 (ego setCycle: CT 1 -1 self))
				(4
					(= local1 1)
					(ego cel: 0)
					(soundFx2 number: 902 loop: 1 play: self)
				)
				(5
					(theGlobalSound number: 783 loop: 1 play:)
					(ego loop: 2 cel: 0 setCycle: CT 8 1 self)
				)
				(6
					(ego cel: 9)
					(soundFx2 number: 902 loop: 1 play:)
					(= cycles 2)
				)
				(7
					(messager say: 12 5 9 0 self)
				)
				(8 (curRoom newRoom: 810))
			)
		else
			(= local1 0)
			(theGame handsOn:)
			(wardrobeDoor show: stopUpd:)
			(ego
				posn: (wardrobeDoor approachX?) (wardrobeDoor approachY?)
				reset: 1
				setPri: 13
			)
			(self dispose:)
		)
	)
)

(instance door of Prop
	(properties
		x 298
		y 161
		noun 10
		sightAngle 40
		approachX 265
		approachY 157
		view 786
		loop 1
		priority 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame handsOff:)
				(ego setPri: -1)
				(soundFx2 number: 901 loop: 1 play:)
				(self setCycle: End self)
			)
			(1
				(if local0
					(messager say: noun theVerb 12)
					(curRoom setScript: (ScriptID 82) 0 hallScr)
				else
					(++ local0)
					(= _approachVerbs
						(| _approachVerbs (approachCode doit: 1))
					)
					(messager say: noun theVerb 11)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(soundFx2 stop:)
		(proc80_2 2)
	)
)

(instance hallScr of Script
	(properties)
	
	(method (init)
		(if
		(= register (not ((ScriptID 80 0) tstFlag: 709 128)))
			(tempGuard1
				view: ((ScriptID 80 6) view?)
				posn: 192 126
				setSpeed: (if (< howFast 2) 3 else 5)
				init:
			)
			(tempGuard2
				posn: 200 128
				setSpeed: (if (< howFast 2) 3 else 5)
				init:
			)
		)
		(super init: &rest)
		(client caller: self)
		((ScriptID 82 1)
			noun: 26
			actions: keyHoleDoVerb
			init: 786 0 0 91 59
		)
	)
	
	(method (dispose)
		(if register
			(tempGuard1 dispose:)
			(tempGuard2 dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(if (< (tempGuard1 x?) 160)
						(tempGuard1 setMotion: MoveTo 192 126 self)
						(tempGuard2 setMotion: MoveTo 200 128)
					else
						((ScriptID 80 0) setFlag: 711 128)
						(tempGuard1 setMotion: MoveTo 128 126 self)
						(tempGuard2 setMotion: MoveTo 120 128)
					)
				else
					(= cycles 4)
				)
			)
			(1
				(if register
					(if (< (tempGuard1 x?) 160)
						(= seconds 4)
					else
						(= seconds 10)
					)
				)
			)
			(2
				((ScriptID 80 0) clrFlag: 711 128)
				(self changeState: 0)
			)
		)
	)
	
	(method (cue)
		(if client (super cue: &rest) else (ego setPri: 13))
	)
)

(instance keyHoleDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(1
				(if ((ScriptID 80 0) tstFlag: 709 128)
					(messager say: 26 1 8)
				else
					(messager say: 26 1 9)
				)
			)
			(else  (= temp0 0))
		)
		(return temp0)
	)
)

(instance tempGuard1 of Actor
	(properties
		noun 26
		sightAngle 180
		loop 4
		priority 14
		signal $4010
		scaleSignal $0001
		scaleX 121
		scaleY 121
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk setStep: 4 2 actions: keyHoleDoVerb)
	)
)

(instance tempGuard2 of Actor
	(properties
		noun 26
		sightAngle 180
		view 724
		loop 4
		cel 1
		priority 14
		signal $4010
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk setStep: 4 2 actions: keyHoleDoVerb)
	)
)

(instance doorJam of View
	(properties
		x 298
		y 161
		onMeCheck $0000
		view 786
		loop 8
		cel 3
	)
)

(instance wardrobeDoor of Prop
	(properties
		x 40
		y 116
		noun 12
		approachX 55
		approachY 161
		view 785
		loop 3
		priority 12
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(curRoom setScript: openWardrobe)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance trunkLid of Prop
	(properties
		x 137
		y 134
		noun 4
		sightAngle 40
		approachX 166
		approachY 149
		view 787
		loop 3
		priority 12
		signal $5010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 35 64)
	)
	
	(method (doVerb)
		(trunk doVerb: &rest)
	)
)

(instance boxLid of Prop
	(properties
		x 189
		y 127
		sightAngle 40
		onMeCheck $0000
		view 787
		loop 4
		signal $5000
	)
)

(instance candles of Prop
	(properties
		x 165
		y 84
		noun 25
		view 785
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init: &rest)
	)
)

(instance doorFrame1ATP of View
	(properties
		x 298
		y 160
		onMeCheck $0000
		view 786
		loop 8
		cel 2
		signal $5010
	)
)

(instance doorFrame2ATP of View
	(properties
		x 298
		y 160
		onMeCheck $0000
		view 786
		loop 8
		cel 3
		signal $4000
	)
)

(instance bedATP of View
	(properties
		x 21
		y 100
		sightAngle 180
		view 786
		loop 8
		priority 12
		signal $5010
	)
	
	(method (doVerb)
		(if (== noun 12)
			(wardrobeDoor doVerb: &rest)
		else
			(super doVerb: &rest)
		)
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (event x?))
		(= temp1 (event y?))
		(= approachY (= approachX (= _approachVerbs 0)))
		(if
			(and
				(super onMe: temp0 temp1)
				(= temp0 (- temp0 nsLeft))
				(= temp1 (- temp1 nsTop))
			)
			(cond 
				(
					(and
						(> temp0 41)
						(or
							(and
								(== (theIconBar curIcon?) (theIconBar at: 1))
								(= _approachVerbs
									(| _approachVerbs (approachCode doit: 5))
								)
								(= approachX 105)
								(= approachY 150)
							)
							1
						)
						(= noun 21)
					)
				)
				(
				(self approachX: 55 approachY: 161 approachVerbs: 5))
				(else (= noun 12))
			)
		)
	)
)

(instance fireplaceATP of View
	(properties
		x 224
		y 126
		view 786
		loop 8
		cel 1
		signal $5010
	)
	
	(method (onMe event &tmp temp0 temp1)
		(asm
			pushi    #x
			pushi    0
			lap      event
			send     4
			sat      temp0
			pushi    #y
			pushi    0
			lap      event
			send     4
			sat      temp1
			pushi    #onMe
			pushi    2
			lst      temp0
			push    
			super    View,  8
			bnt      code_1065
			lst      temp0
			pToa     nsLeft
			sub     
			sat      temp0
			bnt      code_1065
			lst      temp1
			pToa     nsTop
			sub     
			sat      temp1
			bnt      code_1065
			pushi    0
			lat      temp0
			le?     
			bnt      code_1041
			pprev   
			ldi      26
			le?     
			bnt      code_1041
			pushi    30
			lat      temp1
			le?     
			bnt      code_1041
			pprev   
			ldi      51
			le?     
			bnt      code_1041
			ldi      20
			aTop     noun
			bt       code_1065
code_1041:
			pushi    63
			lat      temp0
			le?     
			bnt      code_1061
			pprev   
			ldi      98
			le?     
			bnt      code_1061
			pushi    44
			lat      temp1
			le?     
			bnt      code_1061
			pprev   
			ldi      60
			le?     
			bnt      code_1061
			ldi      24
			aTop     noun
			bt       code_1065
code_1061:
			ldi      23
			aTop     noun
code_1065:
			ret     
		)
	)
)

(instance fireplace of Prop
	(properties
		x 225
		y 117
		heading 180
		noun 23
		sightAngle 40
		view 786
		loop 9
		priority 1
		signal $4010
		cycleSpeed 8
		detailLevel 2
	)
)

(instance trunk of Feature
	(properties
		x 153
		y 145
		z 8
		noun 4
		nsTop 132
		nsLeft 138
		nsBottom 146
		nsRight 156
		sightAngle 40
		approachX 166
		approachY 149
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 35 64)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 5 35 64) (curRoom setScript: openTrunk 0 theVerb))
			((== (approachCode doit: theVerb) -32768)
				(switch theVerb
					(61 (super doVerb: theVerb))
					(else 
						(if ((ScriptID 80 0) tstFlag: 711 4096)
							(messager say: noun 0 14)
						else
							(messager say: noun 0 13)
						)
					)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance box of Feature
	(properties
		x 191
		y 144
		z 19
		heading 180
		noun 14
		nsTop 122
		nsLeft 183
		nsBottom 128
		nsRight 199
		sightAngle 40
		approachX 179
		approachY 150
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: openEbonyBox)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rug of Feature
	(properties
		noun 22
		onMeCheck $0002
	)
)

(instance chandelier of Feature
	(properties
		noun 25
		onMeCheck $0004
	)
)

(instance chestInset of Inset
	(properties
		view 7861
		priority 13
		disposeNotOnMe 1
		noun 8
	)
	
	(method (init)
		(theIconBar disable: 6)
		(ego setPri: 0 stopUpd:)
		(= x (- 160 (/ (CelWide view loop cel) 2)))
		(= y (- 90 (/ (CelHigh view loop cel) 2)))
		(super init: &rest)
		(papers_ChestInset init: self)
		(books_ChestInset init: self)
		(vase_ChestInset init: self)
		(lid_ChestInset init: self)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(if (== caller openTrunk) (theGame handsOff:))
		(super dispose:)
		(ego setPri: 13 startUpd:)
	)
)

(instance ebonyBoxInset of Inset
	(properties
		view 7862
		disposeNotOnMe 1
		noun 15
	)
	
	(method (init)
		(theIconBar disable: 6)
		(ego stopUpd:)
		(= x (- 160 (/ (CelWide view loop cel) 2)))
		(= y (- 90 (/ (CelHigh view loop cel) 2)))
		(super init: &rest)
		(soundFx2 number: 904 play:)
		(paper_BoxInset init: self)
		(pens_BoxInset init: self)
		(dice_BoxInset init: self)
		(lid_BoxInset cel: 1 init: self)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(return
			(cond 
				((and inset (inset handleEvent: event)) 0)
				((& (event type?) evVERB)
					(cond 
						((self onMe: event) (event claimed: 1) (self doVerb: (event message?)))
						(disposeNotOnMe (event claimed: 1) (lid_BoxInset doVerb: 5))
						(else (return 0))
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (messager say: 14 theVerb))
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(messager say: 14)
				else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance letterInset of Inset
	(properties
		view 7863
		priority 15
		disposeNotOnMe 1
		noun 15
	)
	
	(method (init)
		(theIconBar disable: 6)
		(= x (- 160 (/ (CelWide view loop cel) 2)))
		(= y (- 100 (/ (CelHigh view loop cel) 2)))
		(super init: &rest)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose:)
	)
)

(class InsetView of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 180
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 15
		underBits 0
		signal $5011
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		offsetX 0
		offsetY 0
	)
	
	(method (init param1)
		(= x (+ (param1 x?) offsetX))
		(= y (+ (param1 y?) offsetY))
		(super init: &rest)
		(self stopUpd:)
	)
)

(instance papers_ChestInset of InsetView
	(properties
		noun 5
		view 7861
		cel 1
		offsetX 64
		offsetY 50
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(if (ego has: 20) (= temp0 7) else (= temp0 6))
				(messager say: noun theVerb temp0)
			)
			(5
				(if (not (ego has: 20))
					(ego get: 20)
					(theGame givePoints: 1)
					(openTrunk next: viewLetter)
					(chestInset dispose:)
				else
					(messager say: noun theVerb 7)
				)
			)
			(61 (messager say: 4 61))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance viewLetter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 5 6 1 self)
			)
			(1
				(letterInset init: 0 curRoom)
				(= cycles 2)
			)
			(2
				(roomConv
					add: -1 5 5 6 2
					add: -1 5 5 6 3
					add: -1 5 5 6 4
					add: -1 5 5 6 5
					init: self
				)
			)
			(3
				(letterInset caller: self dispose:)
			)
			(4 (= cycles 2))
			(5
				(messager say: 5 5 6 6 self oneOnly: 0)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance books_ChestInset of InsetView
	(properties
		noun 6
		view 7861
		cel 2
		offsetX 81
		offsetY 42
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(papers_ChestInset doVerb: theVerb)
			)
			(61 (messager say: 4 61))
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(messager say: 5)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance vase_ChestInset of InsetView
	(properties
		noun 7
		view 7861
		cel 3
		offsetX 95
		offsetY 39
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(papers_ChestInset doVerb: theVerb)
			)
			(61 (messager say: 4 61))
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(messager say: 5)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance lid_ChestInset of InsetView
	(properties
		view 7861
		cel 4
		offsetX 75
		offsetY 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(chestInset dispose:)
		else
			(chestInset doVerb: theVerb)
		)
	)
)

(instance paper_BoxInset of InsetView
	(properties
		noun 19
		view 7862
		cel 1
		offsetX 68
		offsetY 39
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if (not ((ScriptID 80 0) tstFlag: 709 64))
				(theGame givePoints: 1)
			)
			((ScriptID 80 0) setFlag: 709 64)
			(KQ6Print posn: -1 10 say: 1 noun theVerb 0 1 init:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pens_BoxInset of InsetView
	(properties
		noun 16
		view 7862
		cel 2
		offsetX 69
		offsetY 32
	)
)

(instance dice_BoxInset of InsetView
	(properties
		noun 17
		view 7862
		cel 3
		offsetX 98
		offsetY 35
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (event x?))
		(= temp1 (event y?))
		(if
			(and
				(super onMe: temp0 temp1)
				(= temp0 (- temp0 nsLeft))
				(= temp1 (- temp1 nsTop))
			)
			(if (and (> temp0 17) (= noun 18)) else (= noun 17))
		)
	)
)

(instance lid_BoxInset of InsetView
	(properties
		view 7862
		loop 1
		cel 1
		offsetX 91
		offsetY 24
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(self startUpd:)
				(= cel 0)
				(soundFx2 number: 905 play:)
				(Animate (cast elements?) 0)
				(Animate (cast elements?) 0)
				(ebonyBoxInset dispose:)
			)
			(else 
				(ebonyBoxInset doVerb: theVerb)
			)
		)
	)
)

(instance roomConv of Conversation
	(properties)
)
