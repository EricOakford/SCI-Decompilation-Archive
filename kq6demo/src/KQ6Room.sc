;;; Sierra Script 1.0 - (do not remove this comment)
(script# 900)
(include game.sh) (include "0.shm")
(use Main)
(use Kq6Procs)
(use Print)
(use Game)
(use User)
(use System)

(public
	KQ6Room 0
)

(local
	local0
)
(procedure (localproc_0470)
	(switch curRoomNum
		(160 160)
		(290 34)
		(320 64)
		(390 134)
		(410 154)
		(420 164)
		(440 184)
		(461 5)
		(740 228)
		(750 238)
		(790 22)
		(else  0)
	)
)

(class NewRoomCue of Cue
	(properties
		cuee 0
		cuer 0
		register 0
	)
	
	(method (doit)
		(cuees delete: self)
		(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
		(cuee newRoomCue: register)
		(self dispose:)
	)
)

(class KQ6Room of Room
	(properties
		walkOffEdge 0
		autoLoad 1
	)
	
	(method (init)
		(= local0 0)
		(super init: &rest)
		(if autoLoad
			(if (== modNum -1) (= modNum curRoomNum))
			(Load RES_MESSAGE modNum)
			(Lock 143 modNum 1)
		)
		(if (Btst 103)
			(theGame handsOff:)
			(Bclr 103)
			(self setScript: (ScriptID 89 0))
		)
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(not script)
				(not walkOffEdge)
				(= temp0 ((User alterEgo?) edgeHit?))
				(not (self edgeToRoom: temp0))
			)
			((User alterEgo?) edgeHit: 0 setMotion: 0)
			(switch temp0
				(3 (ego y: 188))
				(1 (ego y: (+ horizon 1)))
				(4 (ego x: 1))
				(2 (ego x: 318))
			)
		)
		(cond 
			(script (script doit:))
			(local0 0)
			(
				(= temp0
					(self edgeToRoom: ((User alterEgo?) edgeHit?))
				)
				(self newRoom: temp0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== modNum -1) (= modNum curRoomNum))
		(if
			(and
				(== (approachCode doit: theVerb) -32768)
				(Message MsgGet modNum noun 0 0 1)
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (setScript theScript &tmp temp0)
		(cond 
			((IsObject theScript) (super setScript: theScript &rest))
			((!= (ego view?) 900)
				(Print
					font: userFont
					posn: -1 10
					addText: N_NOTNOW 0 C_BUSY 1 0 0 0
					init:
				)
			)
			(
				(or
					(and (self script?) (== theScript 905))
					(and
						(== theScript 905)
						(== curRoomNum 480)
						(ego script?)
					)
				)
				(Print
					font: userFont
					posn: -1 10
					addText: N_NOTNOW 0 C_BUSY 1 0 0 0
					init:
				)
			)
			(
			(OneOf theScript 87 85 88 90 190 915 92 93 97 96 905)
				(cond 
					(
						(OneOf curRoomNum
							160 290 320 390 410 420 440 600
							630 640 650 660 670 680 690 740
							750 790 461
						)
						(cond 
							(
							(OneOf curRoomNum 600 630 640 650 660 670 680 690)
								(Print
									font: userFont
									posn: -1 10
									addText: 0 0 88 1 0 0 899
									init:
								)
							)
							((== curRoomNum 461)
								(Print
									font: userFont
									posn: -1 10
									addText: 0 0 5 1 0 0 899
									init:
								)
							)
							((= temp0 (localproc_0470))
								(Print
									font: userFont
									posn: -1 10
									addText: 0 0 curRoomNum 1 0 0 899
									init:
								)
							)
						)
					)
					((self scriptCheck: theScript) (super setScript: (ScriptID theScript) &rest))
				)
			)
			(else (super setScript: (ScriptID theScript) &rest))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if modelessDialog (modelessDialog dispose:))
		(if (not local0)
			(theGame handsOff:)
			(Lock 143 modNum 0)
			(if modelessDialog (modelessDialog dispose:))
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add:
					((NewRoomCue new:)
						cuee: self
						cuer: self
						register: newRoomNumber
						yourself:
					)
			)
			(= local0 1)
		)
	)
	
	(method (newRoomCue)
		(super newRoom: &rest)
	)
	
	(method (translateVerb theVerb)
		(return
			(if (not (OneOf theVerb V_WALK V_LOOK 4 V_TALK))
				(return FALSE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (scriptCheck)
		(return TRUE)
	)
)
