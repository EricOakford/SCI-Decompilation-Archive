;;; Sierra Script 1.0 - (do not remove this comment)
(script# 770)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm770 0
)

(local
	local0
)
(procedure (localproc_0730)
	(return
		(if
			(and
				((ScriptID 80 0) tstFlag: 710 4096)
				((ScriptID 80 0) tstFlag: 710 8192)
				((ScriptID 80 0) tstFlag: 710 16384)
				((ScriptID 80 0) tstFlag: 710 -32768)
				(not ((ScriptID 80 0) tstFlag: 710 16))
			)
			((ScriptID 80 0) setFlag: 710 16)
			(return 1)
		else
			(return 0)
		)
	)
)

(class StolenItem of Feature
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
		look1stSeq 0
		handLookedMsg 0
		flagNum 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(roomConv
					add:
						-1
						noun
						theVerb
						(+ look1stSeq ((ScriptID 80 0) tstFlag: 710 flagNum))
				)
				((ScriptID 80 0) setFlag: 710 flagNum)
				(if (localproc_0730)
					(theGame givePoints: 2)
					(roomConv add: -1 1 0 1)
				)
				(roomConv init:)
			)
			(5
				(messager
					say:
						noun
						theVerb
						(+
							handLookedMsg
							(not ((ScriptID 80 0) tstFlag: 710 flagNum))
						)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(= x (event x?))
		(super onMe: event)
	)
)

(instance rm770 of CastleRoom
	(properties
		noun 3
		picture 770
		style $000a
		south 710
		vanishingX 110
		vanishingY 98
		minScaleSize 55
		maxScaleSize 107
		minScaleY 126
		maxScaleY 154
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany 128 771 770)
		((ScriptID 81 0) clrFlag: 709 1 2)
		(if (cast contains: (ScriptID 80 5))
			((ScriptID 80 5) dispose:)
		)
		(if (cast contains: (ScriptID 80 6))
			((ScriptID 80 6) dispose:)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						15
						179
						6
						189
						0
						189
						0
						-10
						319
						-10
						319
						189
						307
						189
						285
						179
						263
						179
						213
						151
						225
						151
						202
						141
						187
						141
						163
						128
						82
						128
						71
						139
						55
						139
						48
						148
						66
						148
						37
						179
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 172 144 160 150 132 153 106 151 90 143 106 137 155 137
					yourself:
				)
		)
		(features
			add:
				coatOfArms
				goldenFleece
				oakTree
				singingStone
				table
				roomFeatures
				skyLight
			eachElementDo: #init
		)
		(if ((ScriptID 80 0) tstFlag: 711 512)
			((ScriptID 80 0) weddingRemind: 0)
		)
		(if (not ((ScriptID 80 0) tstFlag: 709 8))
			(drape cel: 0 posn: 136 145 14 init: stopUpd:)
		else
			(drape cel: 1 posn: 95 141 0 init: stopUpd:)
		)
		(ego
			init:
			posn: 148 184
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		((ego scaler?) doit:)
		(if (not (Bset 85)) (theGame givePoints: 2))
		(theMusic fadeTo: 770 -1)
		(self setScript: entryScript)
	)
	
	(method (dispose)
		(if ((ScriptID 80 0) tstFlag: 711 512)
			((ScriptID 80 0) weddingRemind: 1)
		)
		(super dispose:)
	)
)

(instance removeDrape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not ((ScriptID 80 0) tstFlag: 710 1024))
					((ScriptID 80 0) setFlag: 710 1024)
					(messager say: 4 5 6 0 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					normal: 0
					setScale: 0
					view: 771
					loop: 0
					cel: 0
					cycleSpeed: 10
					posn: 105 148
					setCycle: EndLoop self
				)
			)
			(2 (= seconds 3))
			(3
				(drape hide:)
				(ego loop: 1 cel: 0 posn: 104 148 setCycle: EndLoop self)
			)
			(4
				(drape posn: 95 141 0 cel: 1 show: stopUpd:)
				(ego loop: 2 cel: 0 posn: 92 150 setCycle: EndLoop self)
			)
			(5 (= seconds 2))
			(6 (ego setCycle: BegLoop self))
			(7
				(ego
					posn: (drape approachX?) (drape approachY?)
					reset: 6
				)
				((ScriptID 80 0) setFlag: 709 8)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance replaceDrape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) clrFlag: 709 8)
				(drape hide: posn: 136 145 14 cel: 0)
				(ego
					normal: 0
					view: 771
					setScale: 0
					loop: 3
					cel: 0
					cycleSpeed: 8
					posn: 104 148
					setCycle: EndLoop self
				)
			)
			(1
				(drape show: stopUpd:)
				(ego
					posn: (drape approachX?) (drape approachY?)
					reset: 6
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance drape of Prop
	(properties
		x 136
		y 145
		z 14
		noun 4
		approachX 95
		approachY 148
		view 770
		priority 11
		signal $1010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 ignoreActors: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not ((ScriptID 80 0) tstFlag: 709 8))
					(roomConv add: -1 noun theVerb 2)
				else
					(roomConv add: -1 noun theVerb 3)
				)
				(if (not ((ScriptID 80 0) tstFlag: 709 256))
					((ScriptID 80 0) setFlag: 709 256)
					(roomConv add: -1 noun theVerb 4)
				)
				(roomConv init:)
			)
			(5
				(if (not ((ScriptID 80 0) tstFlag: 709 8))
					(curRoom setScript: removeDrape)
				else
					(curRoom setScript: replaceDrape)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance coatOfArms of StolenItem
	(properties
		y 144
		noun 8
		onMeCheck $0002
		look1stSeq 19
		handLookedMsg 21
		flagNum 4096
	)
)

(instance goldenFleece of StolenItem
	(properties
		y 144
		noun 6
		onMeCheck $0004
		look1stSeq 11
		handLookedMsg 13
		flagNum 8192
	)
)

(instance oakTree of StolenItem
	(properties
		y 144
		noun 5
		onMeCheck $0008
		look1stSeq 7
		handLookedMsg 9
		flagNum 16384
	)
)

(instance singingStone of StolenItem
	(properties
		y 144
		noun 7
		onMeCheck $0010
		look1stSeq 15
		handLookedMsg 17
		flagNum -32768
	)
)

(instance table of Feature
	(properties
		y 144
		noun 9
		onMeCheck $0020
	)
	
	(method (onMe event)
		(= x (event x?))
		(super onMe: event)
	)
)

(instance skyLight of Feature
	(properties
		x 160
		y 144
		noun 14
		onMeCheck $0400
	)
)

(instance roomFeatures of Feature
	(properties)
	
	(method (onMe event &tmp temp0)
		(= temp0 (OnControl 4 (event x?) (event y?)))
		(= x (event x?))
		(return
			(if
				(or
					(and (& temp0 $0200) (= noun 11) (= y 161))
					(and (& temp0 $0100) (= noun 13) (= y 177))
					(and
						(& temp0 $0080)
						(= noun 10)
						(or (and (> x 190) (= y 145)) (= y 125))
					)
					(and (& temp0 $0040) (= noun 12) (= y 138))
				)
			else
				0
			)
		)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance entryScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
