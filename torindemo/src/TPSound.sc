;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64031)
(include sci.sh)
(use Main)
(use Talker)
(use Sound)

(public
	proc64031_0 0
	proc64031_1 1
	proc64031_2 2
)

(procedure (proc64031_0 theGCurVolume &tmp temp0 soundsFirst)
	(= gCurVolume theGCurVolume)
	(= soundsFirst (sounds first:))
	(while soundsFirst
		(if
			((= temp0 (KList 8 soundsFirst))
				respondsTo: #lUpArrowLoop
			)
			(temp0 lUpArrowLoop:)
		)
		(= soundsFirst (sounds next: soundsFirst))
	)
)

(procedure (proc64031_1 theGSaveThis &tmp temp0 soundsFirst)
	(= gSaveThis theGSaveThis)
	(= soundsFirst (sounds first:))
	(while soundsFirst
		(if
			((= temp0 (KList 8 soundsFirst))
				respondsTo: #lUpArrowLoop
			)
			(temp0 lUpArrowLoop:)
		)
		(= soundsFirst (sounds next: soundsFirst))
	)
)

(procedure (proc64031_2 param1 &tmp [temp0 2])
	(= global229 param1)
)

(class TPSound of Sound
	(properties
		scratch 0
		nodePtr 0
		handle 0
		flags $0000
		number 0
		vol 127
		priority 0
		loop 1
		signal $0000
		prevSignal 0
		dataInc 0
		min 0
		sec 0
		frame 0
		client 0
		owner 0
		type $0000
		minPosn 100
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
		maxPosn -1
		curPosn 0
		incSize 0
	)
	
	(method (play)
		(= prevSignal 0)
		(super play: &rest)
	)
	
	(method (stop)
		(if incSize (incSize dispose:) (= incSize 0))
		(if (!= maxPosn -1)
			(DoAudio 3 audModNum audNoun audVerb audCase audSequence)
			(= maxPosn -1)
		else
			(super stop: &rest)
		)
	)
	
	(method (check)
		(if
			(and
				(!= maxPosn -1)
				(> (- gameTime curPosn) maxPosn)
				(==
					(DoAudio 6 audModNum audNoun audVerb audCase audSequence)
					-1
				)
			)
			(self stop:)
			(if client (client cue: self) (= client 0))
		)
		(if handle (DoSound sndUPDATE_CUES self))
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if incSize (incSize dispose:) (= incSize 0))
			(if client (client cue: self))
		)
	)
	
	(method (pageSize param1)
		(if
		(or (not (self vDownArrowView:)) (!= number param1))
			(if param1
				(self cUpArrowCel: param1)
				(self loop: -1)
				(self play: (self vUpArrowView:) 0)
			else
				(= number 0)
				(self stop:)
			)
		)
	)
	
	(method (vThumbView param1)
		(if
		(or (not (self vDownArrowView:)) (!= number param1))
			(if param1
				(self cUpArrowCel: param1)
				(self loop: -1)
				(self play: (self vUpArrowView:) 0)
			else
				(= number 0)
				(self stop:)
			)
		)
	)
	
	(method (lThumbLoop param1 param2 &tmp temp0)
		(if argc (self cUpArrowCel: param1))
		(if (> argc 1) (= temp0 param2) else (= temp0 0))
		(self loop: 1)
		(self play: (self vUpArrowView:) temp0)
	)
	
	(method (cThumbCel theMinPosn)
		(= minPosn theMinPosn)
		(self lUpArrowLoop:)
	)
	
	(method (vDownArrowView &tmp temp0)
		(= temp0 (if handle (!= prevSignal -1) else 0))
	)
	
	(method (nHeight param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(cond 
				((ResCheck 141 [param1 temp0]) (Load rsAUDIO [param1 temp0]))
				((ResCheck 140 [param1 temp0]) (Load rsCDAUDIO [param1 temp0]))
				(else (MonoOut {Preloaded sound not found: %d} [param1 temp0]))
			)
			(++ temp0)
		)
	)
	
	(method (lDownArrowLoop theAudNoun &tmp theTheAudNoun [temp1 2])
		(if (not (sounds contains: self)) (self init:))
		(self stop:)
		(= audNoun
			[theAudNoun (= audNoun (= audVerb (= audCase (= client 0))))]
		)
		(if (> argc 1) (= audVerb [theAudNoun 1]))
		(if (> argc 2) (= audCase [theAudNoun 2]))
		(if (and (> argc 3) [theAudNoun 3])
			(= audSequence [theAudNoun 3])
		else
			(= audSequence 1)
		)
		(if (> argc 4) (= client [theAudNoun 4]))
		(if (> argc 5)
			(= audModNum [theAudNoun 5])
		else
			(= audModNum curRoomNum)
		)
		(if (> argc 6)
			(= theTheAudNoun [theAudNoun 6])
		else
			(= theTheAudNoun 0)
		)
		(= curPosn gameTime)
		(= maxPosn
			(DoAudio
				2
				audModNum
				audNoun
				audVerb
				audCase
				audSequence
				1
				(MulDiv minPosn global229 100)
			)
		)
		(if (<= maxPosn 0)
			(MonoOut
				{No audio found m:%hu n:%d v:%d c:%d s:%d}
				audModNum
				audNoun
				audVerb
				audCase
				audSequence
			)
			(return)
		)
		(if (and (& msgType $0001) theTheAudNoun)
			(= incSize
				(MakeMessageSubtitle
					audModNum
					audNoun
					audVerb
					audCase
					audSequence
				)
			)
		)
	)
	
	(method (cDownArrowCel param1 theAudNoun &tmp theTheAudNoun)
		(if (not (sounds contains: self)) (self init:))
		(self stop:)
		(= audNoun
			[theAudNoun (= audNoun (= audVerb (= audCase (= client 0))))]
		)
		(if (> argc 2) (= audVerb [theAudNoun 1]))
		(if (> argc 3) (= audCase [theAudNoun 2]))
		(if (and (> argc 4) [theAudNoun 3])
			(= audSequence [theAudNoun 3])
		else
			(= audSequence 1)
		)
		(if (> argc 5)
			(= theTheAudNoun [theAudNoun 4])
		else
			(= theTheAudNoun 0)
		)
		(if (> argc 6)
			(= audModNum [theAudNoun 5])
		else
			(= audModNum curRoomNum)
		)
		(if (& msgType $0001)
			(= incSize
				(MakeMessageSubtitle
					audModNum
					audNoun
					audVerb
					audCase
					audSequence
				)
			)
		)
		(self lThumbLoop: param1 theTheAudNoun)
	)
	
	(method (vUpArrowView)
		(if (== type 1)
			(MulDiv minPosn gCurVolume 100)
			(return)
		)
		(if (== type 0) (MulDiv minPosn gSaveThis 100) (return))
	)
	
	(method (lUpArrowLoop &tmp tPSoundVUpArrowView)
		(if
		(!= (= tPSoundVUpArrowView (self vUpArrowView:)) vol)
			(self setVol: tPSoundVUpArrowView)
		)
	)
	
	(method (cUpArrowCel theNumber)
		(= number theNumber)
		(self nHeight: number)
	)
)
