;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64031)
(include sci.sh)
(use Main)
(use Talker)
(use Sound)
(use System)

(public
	proc64031_0 0
	proc64031_1 1
	proc64031_2 2
)

(procedure (proc64031_0 theGOFileReadWord_3 &tmp temp0 soundsFirst)
	(= gOFileReadWord_3 theGOFileReadWord_3)
	(= soundsFirst (sounds first:))
	(while soundsFirst
		(if
		((= temp0 (List 8 soundsFirst)) respondsTo: #reSyncVol)
			(temp0 reSyncVol:)
		)
		(= soundsFirst (sounds next: soundsFirst))
	)
)

(procedure (proc64031_1 theGOFileReadWord_4 &tmp temp0 soundsFirst)
	(= gOFileReadWord_4 theGOFileReadWord_4)
	(= soundsFirst (sounds first:))
	(while soundsFirst
		(if
		((= temp0 (List 8 soundsFirst)) respondsTo: #reSyncVol)
			(temp0 reSyncVol:)
		)
		(= soundsFirst (sounds next: soundsFirst))
	)
)

(procedure (proc64031_2 theGOFileReadWord_5 &tmp temp0 soundsFirst)
	(= gOFileReadWord_5 theGOFileReadWord_5)
	(= soundsFirst (sounds first:))
	(while soundsFirst
		(if
		((= temp0 (List 8 soundsFirst)) respondsTo: #reSyncVol)
			(temp0 reSyncVol:)
		)
		(= soundsFirst (sounds next: soundsFirst))
	)
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
		relVolPercent 100
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
		audTicks -1
		saveTime 0
		oSubtitle 0
	)
	
	(method (play)
		(= prevSignal 0)
		(super play: &rest)
	)
	
	(method (stop)
		(if oSubtitle (oSubtitle dispose:) (= oSubtitle 0))
		(if (!= audTicks -1)
			(DoAudio 3 audModNum audNoun audVerb audCase audSequence)
			(= audTicks -1)
		else
			(super stop: &rest)
		)
	)
	
	(method (check)
		(if
			(and
				(!= audTicks -1)
				(> (- gameTime saveTime) audTicks)
				(==
					(DoAudio 6 audModNum audNoun audVerb audCase audSequence)
					-1
				)
			)
			(self stop:)
			(if client (client cue: self) (= client 0))
		)
		(if handle (DoSound 17 self))
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if oSubtitle (oSubtitle dispose:) (= oSubtitle 0))
			(if client (client cue: self))
		)
	)
	
	(method (setMusic param1)
		(if
		(or (not (self isPlaying:)) (!= number param1))
			(if param1
				(self newSound: param1)
				(self loop: -1)
				(self play: (self getCurVol:) 0)
			else
				(= number 0)
				(self stop:)
			)
		)
	)
	
	(method (setAmbient param1)
		(if
		(or (not (self isPlaying:)) (!= number param1))
			(if param1
				(self newSound: param1)
				(self loop: -1)
				(self play: (self getCurVol:) 0)
			else
				(= number 0)
				(self stop:)
			)
		)
	)
	
	(method (playSound param1 param2 &tmp temp0)
		(if argc (self newSound: param1))
		(if (> argc 1) (= temp0 param2) else (= temp0 0))
		(self loop: 1)
		(self play: (self getCurVol:) temp0)
	)
	
	(method (setRelVol theRelVolPercent)
		(= relVolPercent theRelVolPercent)
		(self reSyncVol:)
	)
	
	(method (isPlaying &tmp temp0)
		(= temp0 (if handle (!= prevSignal -1) else 0))
	)
	
	(method (preload param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(cond 
				((ResCheck 141 [param1 temp0]) (Load 141 [param1 temp0]))
				((ResCheck 140 [param1 temp0]) (Load 140 [param1 temp0]))
				(else
					(PrintDebug
						{Preloaded sound not found: %d}
						[param1 temp0]
					)
				)
			)
			(++ temp0)
		)
	)
	
	(method (playMessage theAudNoun &tmp theTheAudNoun [temp1 2])
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
		(= saveTime gameTime)
		(= audTicks
			(DoAudio
				2
				audModNum
				audNoun
				audVerb
				audCase
				audSequence
				1
				(MulDiv relVolPercent gOFileReadWord_5 100)
			)
		)
		(if (<= audTicks 0)
			(PrintDebug
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
			(= oSubtitle
				(proc64928_1
					audModNum
					audNoun
					audVerb
					audCase
					audSequence
				)
			)
		)
	)
	
	(method (playSubtitledSound param1 theAudNoun &tmp theTheAudNoun)
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
			(= oSubtitle
				(proc64928_1
					audModNum
					audNoun
					audVerb
					audCase
					audSequence
				)
			)
		)
		(self playSound: param1 theTheAudNoun)
	)
	
	(method (fadeIn param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp0 (if (> argc 1) param2 else 6))
		(= temp1 (if (> argc 2) param3 else 10))
		(= temp2 (if (> argc 3) param4 else 0))
		(= temp3
			(cond 
				((> argc 4) param5)
				((== type 1) 1)
				(else 0)
			)
		)
		(= temp4 (if (> argc 5) param6 else 0))
		(self stop:)
		(self newSound: param1)
		(if temp3
			(self loop: -1)
			(self play: 0 0)
		else
			(self loop: 1)
			(self play: 0 temp4)
		)
		(self fade: (self getCurVol:) temp0 temp1 0 temp2)
	)
	
	(method (fadeOut param1 param2 param3 &tmp temp0 temp1 temp2)
		(= temp0 (if (> argc 0) param1 else 6))
		(= temp1 (if (> argc 1) param2 else 10))
		(= temp2 (if (> argc 2) param3 else 0))
		(if (or (not (self isPlaying:)) (== vol 0))
			(self stop:)
			(if temp2 (temp2 cue:))
		)
		(self fade: 0 temp0 temp1 1 temp2)
	)
	
	(method (fadeRel theRelVolPercent param2 param3 param4 &tmp temp0 temp1 temp2)
		(= temp0 (if (> argc 1) param2 else 6))
		(= temp1 (if (> argc 2) param3 else 10))
		(= temp2 (if (> argc 3) param4 else 0))
		(= relVolPercent theRelVolPercent)
		(self fade: (self getCurVol:) temp0 temp1 0 temp2)
	)
	
	(method (getCurVol)
		(if (== type 1)
			(Min 127 (MulDiv relVolPercent gOFileReadWord_3 100))
			(return)
		)
		(if (== type 0)
			(Min 127 (MulDiv relVolPercent gOFileReadWord_4 100))
			(return)
		)
		(if (== type 2)
			(Min 127 (MulDiv relVolPercent gOFileReadWord_5 100))
			(return)
		)
	)
	
	(method (reSyncVol &tmp tPSoundGetCurVol)
		(if
		(!= (= tPSoundGetCurVol (self getCurVol:)) vol)
			(self setVol: tPSoundGetCurVol)
		)
	)
	
	(method (newSound theNumber)
		(= number theNumber)
	)
)
