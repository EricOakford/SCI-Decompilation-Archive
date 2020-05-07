;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQ6ROOM) ;900
(include game.sh) (include "0.shm") (include "899.shm")
(use Main)
(use KQ6Print)
(use Kq6Procs)
(use Game)
(use User)
(use System)

(public
	KQ6Room 0
)

(local
	local0
)
(procedure (RoomMessage)
	(switch curRoomNum
		(160 C_WEDDING)
		(290 34)
		(320 64)
		(390 134)
		(410 154)
		(420 164)
		(440 184)
		(461 5)
		(740 228)
		(750 C_ENDGAME)
		(790 22)
		(else  0)
	)
)

(class NewRoomCue of Cue
	
	(method (doit)
		(cuees delete: self)
		(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
		(cuee newRoomCue: register)
		(self dispose:)
	)
)

(class KQ6Room of Room
	(properties
		horizon 80
		walkOffEdge 0
		autoLoad 1
	)
	
	(method (init)
		(= local0 0)
		(super init: &rest)
		(if (and (& msgType TEXT_MSG) autoLoad)
			(if (== modNum -1) (= modNum curRoomNum))
			(Load RES_MESSAGE modNum)
			(Lock RES_MESSAGE modNum 1)
		)
		(if (Btst fTeleporting)
			(theGame handsOff:)
			(Bclr fTeleporting)
			(self setScript: (ScriptID 89 0))
		)
	)
	
	(method (doit &tmp nRoom)
		(if
			(and
				(not script)
				(not walkOffEdge)
				(= nRoom ((User alterEgo?) edgeHit?))
				(not (self edgeToRoom: nRoom))
			)
			((User alterEgo?) edgeHit: 0 setMotion: 0)
			(switch nRoom
				(SOUTH (ego y: 188))
				(NORTH (ego y: (+ horizon 1)))
				(WEST (ego x: 1))
				(EAST (ego x: 318))
			)
		)
		(cond 
			(script (script doit:))
			(local0 0)
			(
				(= nRoom
					(self edgeToRoom: ((User alterEgo?) edgeHit?))
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== modNum -1) (= modNum curRoomNum))
		(if
			(and
				(== (approachCode doit: theVerb) leaveIt)
				(Message MsgGet modNum noun 0 0 1)
			)
			(messager say: noun NULL NULL 0 0 modNum)
			1
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (setScript newScript &tmp temp0)
		(cond 
			((IsObject newScript) (super setScript: newScript &rest))
			((!= (ego view?) 900)
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 N_NOTNOW NULL C_BUSY 1 0 0 0
					init:
				)
			)
			(
				(or
					(and (ego script?) (== newScript 905))
					(and (self script?) (== newScript 905))
					(and
						(== newScript 905)
						(== curRoomNum 480)
						(ego script?)
					)
				)
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 N_NOTNOW NULL C_BUSY 1 0 0 0
					init:
				)
			)
			(
			(OneOf newScript 87 85 88 90 190 915 92 93 97 96 905)
				(cond 
					(
						(OneOf curRoomNum
							160 290 320 390 410 420 440 600 630 640
							650 660 670 680 690 740 750 790 461
						)
						(cond 
							(
							(OneOf curRoomNum 600 630 640 650 660 670 680 690)
								(KQ6Print
									font: userFont
									posn: -1 10
									say: 0 0 0 88 1 0 0 899
									init:
								)
							)
							((== curRoomNum 461)
								(KQ6Print
									font: userFont
									posn: -1 10
									say: 0 0 0 5 1 0 0 899
									init:
								)
							)
							((= temp0 (RoomMessage))
								(KQ6Print
									font: userFont
									posn: -1 10
									say: 0 0 0 curRoomNum 1 0 0 899
									init:
								)
							)
						)
					)
					((self scriptCheck: newScript) (super setScript: (ScriptID newScript) &rest))
				)
			)
			(else (super setScript: (ScriptID newScript) &rest))
		)
	)
	
	(method (newRoom n)
		(if modelessDialog (modelessDialog dispose:))
		(if (not local0)
			(theGame handsOff:)
			(Lock RES_MESSAGE modNum 0)
			(if modelessDialog (modelessDialog dispose:))
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add: ((NewRoomCue new:)
					cuee: self
					cuer: self
					register: n
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
