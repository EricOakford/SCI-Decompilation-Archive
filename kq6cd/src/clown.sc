;;; Sierra Script 1.0 - (do not remove this comment)
(script# 274)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	clown 0
	clownExitScr 1
	clownScr 2
)

(instance clown of Actor
	(properties
		x 206
		y 133
		noun 10
		approachX 231
		approachY 140
		view 2721
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachVerbs: 5 2 70
			illegalBits: 0
			ignoreActors:
			setScript: (ScriptID 274 2)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 5 1)
				(messager
					say: noun theVerb (if (Btst 10) 7 else 8) 0 0 270
				)
			)
			((== theVerb 2)
				(if (not (Btst 10))
					(curRoom setScript: (ScriptID 277 0))
				else
					(curRoom setScript: (ScriptID 277 1))
				)
			)
			((OneOf theVerb 40 70)
				(cond 
					((not (Btst 78)) (messager say: noun theVerb 10 0 0 270))
					((Btst 10) (curRoom setScript: (ScriptID 277 2) 0 theVerb))
					(else
						(theGame givePoints: 4)
						(Bset 10)
						(curRoom
							setScript: (ScriptID 275 0) 0 (if (== theVerb 40) 1 else 0)
						)
					)
				)
			)
			((OneOf theVerb 69 13) (super doVerb: theVerb &rest))
			(
				(or
					(OneOf theVerb 45 8 14 30 47 15 18)
					(OneOf theVerb 32 12 62 63 65 66 67)
				)
				(if (== theVerb 15) (= theVerb 18))
				(if (== theVerb 67) (= theVerb 63))
				(curRoom setScript: (ScriptID 277 2) 0 theVerb)
			)
			(else (curRoom setScript: (ScriptID 277 2)))
		)
	)
)

(instance clownScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client view: 2721 loop: 0)
				(= cycles 1)
			)
			(1 (= seconds (Random 5 20)))
			(2
				(client cel: 0 setCycle: EndLoop self)
			)
			(3
				(client stopUpd:)
				(= state 0)
				(self cue:)
			)
		)
	)
)

(instance clownExitScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(Bclr 53)
		(if (not (OneOf currentAct 3 4))
			(Bclr 54)
		else
			(Bset 54)
		)
		(clown dispose:)
		(clown delete:)
		(DisposeScript 274)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(DisposeScript 275)
				(ego stopUpd:)
				(clown
					posn: 201 137
					view: 2741
					setLoop: 0
					scaleX: 121
					scaleY: 121
					setStep: 5 3
					setScale:
					setCycle: Walk
					setSpeed: 4
					setMotion: PolyPath 107 135 self
				)
			)
			(2
				(clown
					setPri: 1
					view: 274
					loop: 1
					cel: 0
					posn: 72 135
					cycleSpeed: 6
					setCycle: EndLoop
				)
				(soundFx2 number: 901 loop: 1 play:)
				((ScriptID 270 4) setCycle: EndLoop self)
			)
			(3
				((ScriptID 270 4) setCycle: BegLoop self)
			)
			(4
				(clown dispose:)
				(soundFx2 number: 902 loop: 1 play:)
				(ego startUpd:)
				(= cycles 2)
			)
			(5
				((ScriptID 270 4) stopUpd:)
				((ScriptID 10 0) setIt: 2)
				(theMusic fade: 0 20 15 0)
				(= ticks 45)
			)
			(6
				(UnLoad 128 2741)
				(UnLoad 128 274)
				(theMusic number: 240 loop: -1 play: 0 fade: 70 10 15 0)
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(= seconds 13)
			)
			(7
				((ScriptID 270 5) init:)
				((ScriptID 10 0) clrIt: 2)
				(self dispose:)
			)
		)
	)
)
