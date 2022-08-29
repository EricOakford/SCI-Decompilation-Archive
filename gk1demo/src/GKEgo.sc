;;; Sierra Script 1.0 - (do not remove this comment)
(script# GKEGO)
(include game.sh) (include "18.shm")
(use Main)
(use Procs)
(use StopWalk)
(use Grooper)
(use Ego)
(use System)

(public
	GKEgo 0
	gStopGroop 1
)

(class GKEgo of Ego
	(properties
		noun N_GABRIEL
		modNum GKEGO
		talking 0
		oldView 0
		oldLoop 0
		oldCel 0
		oldSig 0
	)
	
	(method (doit)
		(super doit:)
		(= edgeHit
			(cond 
				((<= x westEdge) WEST)
				((>= x eastEdge) EAST)
				((>= y 141) SOUTH)
				((<= y (curRoom horizon?)) NORTH)
				(else 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(switch (Random 0 5)
					(0
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_1 1 0 GKEGO)
					)
					(1
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_2 1 0 GKEGO)
					)
					(2
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_3 1 0 GKEGO)
					)
					(3
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_4 1 0 GKEGO)
					)
					(4
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_5 1 0 GKEGO)
					)
					(5
						(messager say: N_GABRIEL V_TALK C_TALK_SELF_6 1 0 GKEGO)
					)
				)
			)
			(else 
				(if (Message MsgGet modNum noun theVerb NULL 1)
					(messager say: noun theVerb NULL 0 0 GKEGO)
				else
					(messager say: noun NULL NULL 0 0 GKEGO)
				)
			)
		)
	)
	
	(method (normalize theLoop theCel)
		(if (> argc 0)
			(ego loop: theLoop)
		)
		(ego
			view: (if (> argc 1) theCel else 900)
			edgeHit: 0
			signal: SKIPCHECK
			z: 0
			setLoop: -1
			setLoop: egoLooper
			setPri: -1
			setMotion: 0
			illegalBits: 0
			ignoreActors: 0
			setStep: 3 2
			setCycle: StopWalk -1
			setSpeed: (theGame currentSpeed?)
		)
	)
	
	(method (getPoints pFlag pValue pSound)
		(if (not (Btst pFlag))
			(Bset pFlag)
			(+= score pValue)
			(if (and (theGame barUp?) (not (OneOf curRoomNum 50)))
				(theIconBar updateScore:)
			)
			(if (and (== argc 3) pSound)
				(theSound3 number: 11 play:)
			else
				(theSound3 number: 10 play:)
			)
		)
	)
)

(instance gStopGroop of GradualLooper

	(method (cue &tmp theLoop)
		(if (not (ego scaleSignal?))
			(= theLoop (GKEgo loop?))
			(if (== client GKEgo)
				(cond 
					((< theLoop loopS)
						(GKEgo setStep: 4 2)
					)
					((< theLoop loopSE)
						(GKEgo setStep: 3 1)
					)
					((< theLoop loopNE)
						(GKEgo setStep: 3 3)
					)
					(else
						(GKEgo setStep: 3 2)
					)
				)
			)
		)
		(super cue:)
	)
)
