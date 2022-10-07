;;; Sierra Script 1.0 - (do not remove this comment)
(script# 745)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Motion)
(use Actor)
(use System)

(public
	closeupWedding 0
)

(instance closeupWedding of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(Bset 99)
		(curRoom noun: 3)
		((ScriptID 80 0) setFlag: 710 2048)
		(walkHandler addToFront: self)
		(directionHandler addToFront: self)
	)
	
	(method (dispose)
		(theIconBar disable:)
		(walkHandler delete: self)
		(directionHandler delete: self)
		(genieHead dispose: delete:)
		(vizierHead dispose: delete:)
		(saladinArm dispose: delete:)
		(alexHead dispose: delete:)
		(priestHead dispose: delete:)
		(saladinHead dispose: delete:)
		(glint1 dispose: delete:)
		(glint2 dispose: delete:)
		(curRoom drawPic: 740)
		(Bclr 99)
		(curRoom noun: 3)
		(cast eachElementDo: #show eachElementDo: #stopUpd)
		(super dispose:)
		(ego startUpd:)
		(if (Btst 156) (ego posn: 149 144))
		(UnLoad 128 160)
		(UnLoad 128 161)
		(UnLoad 128 902)
		(UnLoad 129 160)
		(UnLoad 143 160)
		(DisposeScript 1005)
		(DisposeScript 745)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #startUpd)
				(theIconBar disable:)
				(= cycles 1)
			)
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 160)
				(theMusic number: 740 loop: -1 play:)
				(vizierHead addToPic:)
				(saladinArm init: stopUpd:)
				(saladinHead init:)
				(alexHead init:)
				(priestHead init:)
				(= seconds (= cycles 2))
			)
			(2 (theIconBar enable:))
			(3
				((ScriptID 740 7)
					add: 160 1 0 1 1
					add: 160 1 0 1 2
					add: 160 1 0 1 3
					add: 160 1 0 1 4
					add: 160 1 0 1 5
					add: 160 1 0 1 6
					init: self
				)
			)
			(4
				(glint1 init: setCycle: EndLoop self)
				(glint2 init: setCycle: EndLoop)
			)
			(5
				(theMusic number: 746 loop: -1 play:)
				(glint1 setCycle: BegLoop self)
				(glint2 setCycle: BegLoop)
			)
			(6
				((ScriptID 740 7)
					add: 160 1 0 1 7
					add: 160 1 0 1 8
					init: self
				)
			)
			(7
				(glint1 dispose:)
				(glint2 dispose:)
				(genieHead init: cel: 0)
				(= cycles 10)
			)
			(8
				(DisposeScript 939)
				(saladinArm cel: 2 startUpd:)
				(if
				(not (= register ((ScriptID 80 0) tstFlag: 709 128)))
					(saladinArm setCycle: BegLoop self)
				else
					(saladinArm setScript: drawSword self)
					(= cycles 1)
				)
			)
			(9
				(if (not register)
					(= next 0)
					(messager say: 1 0 2 0 self 160)
				else
					(theGame handsOn:)
					(++ state)
				)
			)
			(10
				(saladinArm setScript: 0 setCycle: EndLoop self)
			)
			(11 (= cycles 3))
			(12
				(theGame handsOff:)
				(theIconBar disable:)
				(if (not next)
					(if (not register)
						(UnLoad 128 738)
						((ScriptID 740 5) view: 7424 loop: 0 cel: 0 setCycle: 0)
						(= next (ScriptID 742 3))
					else
						((ScriptID 744 1) register: 29)
						(= next (ScriptID 744 1))
					)
				)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (theIconBar at: 0) (theIconBar curIcon?))
				(& (event type?) $1040)
			)
			(event claimed: 1)
			(= next (ScriptID 744 1))
			(self cue:)
		)
		(event claimed?)
	)
	
	(method (setScript)
		(= seconds 0)
		(super setScript: &rest)
	)
)

(instance showMirror of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(saladinHead dispose: delete:)
		(priestHead dispose: delete:)
		(alexHead dispose: delete:)
		(mirror dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(drawSword caller: 0)
				(saladinArm setScript: 0)
				(if (saladinArm cel?)
					(saladinArm setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(messager say: 4 13 0 1 self 160)
			)
			(2
				(genieHead init: cel: 1 setCycle: EndLoop)
				(mirror init: cel: 0 cycleSpeed: 15 setCycle: EndLoop self)
			)
			(3
				(theGame givePoints: 3)
				(saladinArm addToPic:)
				(priestHead cel: 1 addToPic:)
				(saladinHead cel: 1 addToPic:)
				(alexHead cel: 1 addToPic:)
				(genieHead addToPic:)
				(mirror addToPic:)
				(= cycles 18)
			)
			(4
				(messager say: 4 13 0 2 self 160)
			)
			(5
				(client seconds: 0 next: (ScriptID 744 0))
				((ScriptID 740 5) view: 7424 loop: 0 cel: 0 setCycle: 0)
				(theIconBar disable:)
				(client dispose:)
			)
		)
	)
)

(instance showReplicaLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 7 56 0 0 self 160)
			)
			(1
				(closeupWedding next: (ScriptID 744 1) changeState: 12)
			)
		)
	)
)

(instance drawSword of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(if (> (saladinArm cel?) 0)
					(saladinArm cel: (- (saladinArm cel?) 1) startUpd:)
					(= cycles 2)
				else
					(self dispose:)
				)
			)
			(2
				(saladinArm stopUpd:)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(class CloseupProp of Prop
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
		sightAngle 26505
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
		priority 0
		underBits 0
		signal $0000
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		controlColor 0
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
			else
				(& controlColor (OnControl 4 (event x?) (event y?)))
			)
		)
	)
)

(instance genieHead of CloseupProp
	(properties
		x 147
		y 58
		noun 4
		modNum 160
		view 160
		loop 1
		cel 1
		priority 9
		signal $0010
		controlColor 2
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 33 18) (messager say: noun 18 0 0 0 modNum))
			((OneOf theVerb 57 58 59 60 43)
				((ScriptID 740 7) add: modNum noun 43 0 1)
				(if (!= theVerb 43)
					((ScriptID 740 7) add: modNum noun 57 0 1)
				else
					((ScriptID 740 7) add: modNum noun 43 0 2)
				)
				((ScriptID 740 7) init:)
			)
			((OneOf theVerb 56 2)
				(theGame handsOff:)
				(closeupWedding seconds: 0 next: (ScriptID 744 1))
				(messager say: noun theVerb 0 0 closeupWedding modNum)
			)
			((OneOf theVerb 67 63)
				(theGame handsOff:)
				(Bset 156)
				(closeupWedding seconds: 0 next: (ScriptID 744 1))
				(messager say: 4 67 0 0 closeupWedding 160)
			)
			(else
				(switch theVerb
					(13
						(closeupWedding setScript: showMirror)
					)
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance vizierHead of CloseupProp
	(properties
		x 120
		y 53
		noun 7
		modNum 160
		view 160
		loop 3
		controlColor 4
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 33 18) (= theVerb 65) (super doVerb: theVerb))
			((OneOf theVerb 57 58 59 60 43) (= theVerb 43) (super doVerb: theVerb))
			((== theVerb 56) (closeupWedding setScript: showReplicaLamp))
			(else (super doVerb: theVerb))
		)
	)
)

(instance alexHead of CloseupProp
	(properties
		x 51
		y 50
		view 160
		loop 2
		controlColor 8
	)
	
	(method (doVerb)
		(ego doVerb: &rest)
	)
)

(instance priestHead of CloseupProp
	(properties
		x 270
		y 56
		noun 8
		modNum 160
		view 160
		loop 4
		controlColor 32
	)
)

(instance saladinArm of CloseupProp
	(properties
		x 5
		y 79
		noun 5
		modNum 160
		view 160
		loop 7
		cel 3
		controlColor 16
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 33 18)
			(messager say: noun 33 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance saladinHead of View
	(properties
		x 10
		y 24
		view 160
		loop 5
	)
	
	(method (doVerb)
		(saladinArm doVerb: &rest)
	)
)

(instance mirror of Prop
	(properties
		x 35
		y 102
		view 160
		cel 2
	)
)

(instance glint1 of Prop
	(properties
		x 179
		y 47
		view 902
		priority 15
		signal $6000
		cycleSpeed 4
	)
)

(instance glint2 of Prop
	(properties
		x 169
		y 47
		view 902
		priority 15
		signal $6000
		cycleSpeed 4
	)
)
