;;; Sierra Script 1.0 - (do not remove this comment)
(script# 820)
(include system.sh)
(use Main)
(use CastleRoom)
(use Print)
(use Conv)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Window)
(use Motion)
(use Actor)
(use System)

(public
	rm820 0
	noWayOut 1
	roomConv 2
	dungeonDoor 3
	flame 4
	wallReflection 5
)

(local
	local0
	local1
)
(instance rm820 of CastleRoom
	(properties
		noun 3
		picture 820
		style $000a
		horizon 0
		vanishingX 186
		vanishingY 92
		minScaleSize 89
		maxScaleSize 113
		minScaleY 130
		maxScaleY 144
	)
	
	(method (init)
		(LoadMany 128 825 822)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 10 166 10 174 301 174 301 161 280 147 147 147 147 132 95 132
					yourself:
				)
		)
		((ScriptID 1015 6) x: 19 y: 41)
		((ScriptID 1015 7) x: 19 y: 77)
		(features add: bed torch gargoyle eachElementDo: #init)
		(super init: &rest)
		(flame setCycle: Forward init:)
		(wallReflection setCycle: RandCycle init:)
		(dungeonDoor cel: 3 setPri: 10 init: stopUpd:)
		(extraView addToPic:)
		(ant init: setScript: antScript)
		(ego
			init:
			reset: 0
			posn: 43 143
			setPri: 9
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		((ego scaler?) doit:)
		(if ((ScriptID 80 0) tstFlag: 709 8192)
			(if (cast contains: (ScriptID 80 5))
				((ScriptID 81 0) resetGuard: (ScriptID 80 5) 1)
				((ScriptID 80 5) dispose:)
			)
			(if (cast contains: (ScriptID 80 6))
				((ScriptID 81 0) resetGuard: (ScriptID 80 6) 2)
				((ScriptID 80 6) dispose:)
			)
			((ScriptID 81 0) clrFlag: 709 1 2)
			(self setScript: (ScriptID 821 0))
			(theMusic fadeTo: 824 -1)
			(= local0 1)
		else
			(self setScript: enterDungeon)
			(if
				(and
					(OneOf ((ScriptID 80 0) dungeonEntered?) 1 2)
					(not ((ScriptID 80 0) tstFlag: 709 -32768))
				)
				(theMusic fadeTo: 820 -1)
				(if (not (ego has: 17)) (dungeonDoor approachX: 111))
				(enterDungeon next: (ScriptID 822 0))
			else
				(theMusic fadeTo: 824 -1)
			)
		)
	)
	
	(method (dispose)
		(ego setPri: -1)
		(super dispose: &rest)
		(DisposeScript 991)
		(DisposeScript 964)
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 2)
				(OneOf ((ScriptID 80 0) dungeonEntered?) 1 2)
				(not ((ScriptID 80 0) tstFlag: 709 -32768))
			)
			(messager say: 3 2 20 1)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance enterDungeon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					setMotion: MoveTo (dungeonDoor approachX?) (dungeonDoor approachY?) self
				)
			)
			(2
				(dungeonDoor setCycle: BegLoop self)
			)
			(3
				(soundFx2 number: 822 loop: 1 play:)
				(dungeonDoor setPri: -1 stopUpd:)
				(if
					(or
						((ScriptID 81 0) tstFlag: 709 1)
						((ScriptID 81 0) tstFlag: 709 2)
					)
					(messager say: 1 0 7 1 self)
				else
					(= cycles 2)
				)
				(if (cast contains: (ScriptID 80 5))
					((ScriptID 81 0) resetGuard: (ScriptID 80 5) 1)
					((ScriptID 80 5) dispose:)
				)
				(if (cast contains: (ScriptID 80 6))
					((ScriptID 81 0) resetGuard: (ScriptID 80 6) 2)
					((ScriptID 80 6) dispose:)
				)
				((ScriptID 81 0) clrFlag: 709 1 2)
			)
			(4
				(if (not next)
					(theGame handsOn:)
				else
					(= next 0)
					((ScriptID 822 1) init:)
				)
				(ego reset: 0)
				(self dispose:)
			)
		)
	)
)

(instance exitDungeon of Script
	(properties)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(if (and (== state 1) (& temp0 $4000)) (self cue:))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(dungeonDoor hide:)
				(ego
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					view: 821
					loop: 4
					cel: 0
					posn: 56 109
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(soundFx2 number: 821 loop: 1 play:)
				(dungeonDoor setPri: 10 cel: 3)
				(ego setCycle: EndLoop self)
			)
			(2
				(dungeonDoor show: stopUpd:)
				(soundFx2 stop:)
				(ego
					posn: (dungeonDoor approachX?) (dungeonDoor approachY?)
					reset: 1
					setPri: 9
					setMotion: MoveTo 0 143 self
				)
			)
			(3
				(ego hide:)
				(dungeonDoor setCycle: BegLoop self)
			)
			(4
				(soundFx2 number: 822 loop: 1 play: self)
				(ego setPri: -1)
			)
			(5 (curRoom newRoom: 710))
		)
	)
)

(instance antScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(= state (+ state (* (Random 0 1) 3)))
				(ant hide: setPri: 15 ignoreActors: setCycle: Walk)
				(= seconds (Random 7 25))
			)
			(1
				(ant show:)
				(if (= register (Random 0 1))
					(ant
						setLoop: 6
						posn: 220 201 0
						setMotion: MoveTo 172 167 self
					)
				else
					(ant
						setLoop: 5
						posn: 106 195 0
						setMotion: MoveTo 155 169 self
					)
				)
			)
			(2 (= seconds (Random 1 10)))
			(3
				(= state -1)
				(ant setPri: 14)
				(if register
					(ant
						setLoop: 8
						posn: 164 195 28
						setMotion: MoveTo 164 213 self
					)
				else
					(ant
						setLoop: 7
						posn: 162 195 24
						setMotion: MoveTo 162 209 self
					)
				)
			)
			(4
				(ant
					show:
					setLoop: 5
					posn: 0 189 0
					setMotion: MoveTo 44 172 self
				)
			)
			(5 (= seconds (Random 2 10)))
			(6
				(= state -1)
				(ant loop: 7 setMotion: MoveTo 88 197 self)
			)
		)
	)
)

(instance noWayOut of Script
	(properties)
	
	(method (changeState newState &tmp printRet [temp1 200])
		(switch (= state newState)
			(0
				(ant setScript: 0)
				(theGame handsOff:)
				(theMusic fadeTo: 823 1)
				(= cycles 2)
			)
			(1
				(ego setMotion: PolyPath 90 144 self)
			)
			(2
				(ego
					view: 821
					normal: 0
					setPri: 12
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 93 144
					loop: 5
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3
				(soundFx2 number: 825 setLoop: -1 play:)
				(ego loop: 6 cel: 0 setCycle: Forward)
				(= seconds 4)
			)
			(4
				(soundFx2 stop:)
				(ego setCycle: 0)
				(= cycles 3)
			)
			(5 (messager say: 1 0 2 1 self))
			(6 (messager say: 1 0 2 2 self))
			(7
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98 10)
				(Message 1 @temp1) ;msgNEXT
				(Display @temp1
					p_at 30 11
					p_width 260
					p_color 16
					p_font userFont
					p_mode teJustCenter
				)
				(Display @temp1
					p_at 29 10
					p_width 260
					p_color 47
					p_font userFont
					p_mode teJustCenter
				)
				(ego
					view: 8901
					loop: 0
					cel: 0
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 160 43
					setMotion: 0
					show:
				)
				(theMusic number: 970 loop: 1 play:)
				(= cycles 2)
			)
			(8
				(theGame setCursor: normalCursor)
				(repeat
					(= printRet
						(Print
							window: DeathWindow
							addText: {Please select:} 60 0
							posn: 70 130
							addButton: 1 {Restore} 0 15
							addButton: 2 {Restart} 70 15
							addButton: 3 {Quit} 140 15
							init:
						)
					)
					(switch printRet
						(1
							(theGame restore:)
						)
						(2
							(theGame restart: 1)
						)
						(3
							(= quit 1)
							(break)
						)
					)
				)
			)
		)
	)
)

(instance doorLocked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 90 144 self)
			)
			(1
				(ego
					view: 821
					normal: 0
					setPri: 12
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 93 144
					loop: 5
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2
				(ego loop: 6 cel: 0 setCycle: Forward)
				(= seconds 4)
			)
			(3
				(ego setCycle: 0)
				(= cycles 3)
			)
			(4
				(messager say: 5 5 15 0 self)
			)
			(5
				(ego loop: 5 cel: 2 cycleSpeed: 8 setCycle: BegLoop self)
			)
			(6
				(ego reset: 1 posn: 90 144)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance unlockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(dungeonDoor hide:)
				(ego
					normal: 0
					setScale: 0
					view: 823
					loop: 0
					cel: 0
					cycleSpeed: 8
					posn: 56 109
					setCycle: CycleTo 5 1 self
				)
			)
			(1 (= cycles 50))
			(2
				(ego cel: 6)
				(soundFx number: 781 loop: 1 play: self)
			)
			(3
				(messager say: 5 35 15 0 self)
			)
			(4
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
				(soundFx number: 821 loop: 1 play:)
			)
			(5
				((ScriptID 80 0) setFlag: 709 4096)
				(curRoom newRoom: 710)
			)
		)
	)
)

(instance lookOutDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 90 144 self)
			)
			(1
				(ego
					view: 821
					normal: 0
					setPri: 12
					setScale: 0
					scaleX: 128
					scaleY: 128
					posn: 93 144
					loop: 5
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2 (messager say: 7 1 0 0 self))
			(3 (ego setCycle: BegLoop self))
			(4
				(theGame handsOn:)
				(ego reset: 1 posn: 90 144)
				(self dispose:)
			)
		)
	)
)

(instance extraView of View
	(properties
		onMeCheck $0000
		view 821
		loop 3
		signal $4010
	)
	
	(method (init)
		(switch ((ScriptID 80 0) dungeonEntered?)
			(3
				(self cel: 1 x: 211 y: 125 noun: 8)
			)
			(else 
				(self cel: 0 x: 159 y: 108 noun: 9 onMeCheck: 26505)
			)
		)
		(super init: &rest)
	)
)

(instance flame of Prop
	(properties
		x 116
		y 76
		noun 11
		view 821
	)
	
	(method (doVerb theVerb)
		(torch doVerb: theVerb)
	)
)

(instance wallReflection of Prop
	(properties
		x 115
		y 72
		onMeCheck $0000
		view 821
		loop 1
		signal $4010
		cycleSpeed 12
	)
)

(instance dungeonDoor of Prop
	(properties
		x 56
		y 109
		noun 5
		sightAngle 45
		approachX 91
		approachY 145
		approachDist 2
		view 821
		loop 2
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 35 2)
	)
	
	(method (doVerb theVerb)
		(if (== noun 5)
			(switch theVerb
				(35
					(cond 
						((not local0) (messager say: noun theVerb 14))
						(((ScriptID 80 0) tstFlag: 709 8192)
							((ScriptID 80 0) setFlag: 709 4096)
							(= local0 0)
							(curRoom setScript: unlockDoor)
						)
					)
				)
				(5
					(if (not local0)
						(curRoom setScript: exitDungeon)
					else
						(curRoom setScript: doorLocked)
					)
				)
				(2
					(messager
						say: noun theVerb (+ 14 ((ScriptID 80 0) tstFlag: 709 8192))
					)
				)
				(else 
					(if (== (approachCode doit: theVerb) -32768)
						(messager
							say: noun 0 (+ 14 ((ScriptID 80 0) tstFlag: 709 8192))
						)
					else
						(super doVerb: theVerb)
					)
				)
			)
		else
			(switch theVerb
				(2
					(if local0
						(messager say: noun theVerb 15)
					else
						(messager say: noun theVerb 14)
					)
				)
				(1
					(curRoom setScript: lookOutDoor)
				)
				(else 
					(= noun 5)
					(self doVerb: theVerb)
				)
			)
		)
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (event x?))
		(= temp1 (event y?))
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: 4))
				(== (theIconBar curInvIcon?) (inventory at: 44))
			)
			(= approachX 82)
			(= approachY 144)
		else
			(= approachX 91)
			(= approachY 145)
		)
		(= temp0 (- temp0 nsLeft))
		(= temp1 (- temp1 nsTop))
		(if
			(and
				(<= 5 temp0)
				(<= temp0 15)
				(<= 11 temp1)
				(<= temp1 24)
			)
			(= noun 7)
		else
			(= noun 5)
		)
		(super onMe: event)
	)
)

(instance ant of Actor
	(properties
		x 149
		y 185
		noun 13
		view 820
		loop 8
	)
)

(instance bed of Feature
	(properties
		noun 8
		onMeCheck $0002
	)
)

(instance torch of Feature
	(properties
		noun 11
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager say: noun theVerb (+ 14 local0))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gargoyle of Feature
	(properties
		x 56
		y 144
		noun 12
		onMeCheck $0008
	)
)

(instance roomConv of Conversation
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(= caller 0)
	)
)

(instance DeathWindow of SysWindow
	(properties)
	
	(method (open)
		(= color 47)
		(= back 0)
		(super open: &rest)
	)
)
