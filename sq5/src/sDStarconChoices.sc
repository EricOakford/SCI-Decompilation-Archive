;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include sci.sh)
(use Main)
(use rm201)
(use eureka)
(use System)

(public
	sAsteroidChoices 0
	sFStarconChoices 1
	sDStarconChoices 2
	sDAlienChoices 3
	sFAlienChoices 4
	sFCloroxChoices 5
	sFNoiseChoices 6
	sFDefault 7
	sFGenericChoices 8
	sDCloroxChoices 9
	sFThrakusChoices 10
	sDThrakusChoices 11
	sFBeforeGoliath 12
	sFAfterClorox 13
	sDAfterClorox 15
	sDDefaultChoices 16
)

(local
	local0
)
(procedure (localproc_00a2 param1 param2 param3)
	((ScriptID 201 13)
		normal: 0
		keepWindow: 1
		curNoun: param1
		curCase: param3
		curVerb: param2
	)
	(theGame handsOn:)
	(theIconBar select: (theIconBar at: 2))
	(theGame setCursor: 982)
)

(procedure (localproc_00ee param1 param2 param3)
	((ScriptID 201 12)
		normal: 0
		keepWindow: 1
		curNoun: param1
		curCase: param3
		curVerb: param2
	)
	(theGame handsOn:)
	(theIconBar select: (theIconBar at: 2))
	(theGame setCursor: 982)
)

(instance sDStarconChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00ee 4 0 5)
				(= cycles 1)
			)
			(1
				(messager say: 4 0 5 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(messager say: 4 0 2 0 self 202)
						)
						(2
							(messager say: 4 0 3 0 self 202)
						)
						(3
							(messager say: 4 0 4 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFStarconChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 8)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 8 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 9 0 self 202)
						)
						(2
							(= local0 1)
							(messager say: 12 0 10 1 3 self 202)
						)
						(3
							(messager say: 12 0 11 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if local0
					(self setScript: sDrooleComments self)
				else
					(= cycles 1)
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance sDAlienChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
				(localproc_00ee 26 0 36)
			)
			(1
				(messager say: 26 0 36 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(messager say: 26 0 37 0 self 202)
						)
						(2
							(self setScript: sDTree6Choice2 self)
						)
						(3
							((ScriptID 201 15) talkWidth: 250)
							(messager say: 26 0 38 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3
				((ScriptID 201 15) talkWidth: 0)
				(self dispose:)
			)
		)
	)
)

(instance sDDefaultChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
				(localproc_00ee 26 0 47)
			)
			(1
				(messager say: 26 0 47 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							((ScriptID 201 12) talkWidth: 127)
							(messager say: 26 0 48 0 self 202)
						)
						(2
							(messager say: 26 0 49 0 self 202)
						)
						(3
							(messager say: 26 0 50 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3
				((ScriptID 201 12) talkWidth: 120)
				(self dispose:)
			)
		)
	)
)

(instance sDTree6Choice2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 26 0 40 1 2 self 202)
			)
			(1 (proc201_6 self))
			(2
				(messager say: 26 0 40 3 4 self 202)
			)
			(3
				(self setScript: (ScriptID 201 4) self)
			)
			(4 (self dispose:))
		)
	)
)

(instance sFAlienChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 32)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 32 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 33 0 self 202)
						)
						(2
							(messager say: 12 0 34 0 self 202)
						)
						(3
							(if (< global127 3)
								(messager say: 12 0 35 0 self 202)
							else
								(messager say: 25 0 22 0 self 202)
							)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFNoiseChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 27)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 27 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 28 0 self 202)
						)
						(2
							(messager say: 12 0 29 0 self 202)
						)
						(3
							(if (< global127 3)
								(messager say: 12 0 30 0 self 202)
							else
								(messager say: 25 0 22 0 self 202)
							)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFDefault of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 41)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 41 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(if (Btst 30)
								(messager say: 4 0 3 2 self)
							else
								(messager say: 12 0 43 0 self 202)
							)
						)
						(2
							(messager
								say: 12 0 44 (+ (eureka destination?) 1) self 202
							)
						)
						(3
							(cond 
								((< global127 3) (messager say: 25 0 21 1 self 202))
								((and (not (Btst 30)) (== global127 3)) (messager say: 25 0 22 1 self 202))
								((and (Btst 30) (not (Btst 93))) (messager say: 25 0 26 1 self 202))
								((and (== global142 1) (not (Btst 94))) (messager say: 25 0 24 0 self 202))
								((and (>= global142 1) (not (Btst 75))) (messager say: 25 0 25 0 self 202))
								(else
									(messager
										say: 12 0 44 (+ (eureka destination?) 1) self 202
									)
								)
							)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFGenericChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 42)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 42 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 45 0 self 202)
						)
						(2
							(messager say: 12 0 46 0 self 202)
						)
						(3
							(if (< global127 3)
								(messager say: 12 0 60 0 self 202)
							else
								(messager say: 25 0 22 0 self 202)
							)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sDCloroxChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00ee 26 0 55)
				(= cycles 1)
			)
			(1
				(messager say: 26 0 55 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(theGame handsOff:)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(messager say: 26 0 56 0 self 202)
						)
						(2
							(messager say: 26 0 57 0 self 202)
						)
						(3
							(messager say: 26 0 58 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFCloroxChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 51)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 51 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(theGame handsOff:)
				(proc201_27 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(self setScript: sFTree9Choice1 self)
						)
						(2
							(messager say: 12 0 53 0 self 202)
						)
						(3
							(messager say: 12 0 54 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3
				(proc201_27 1)
				(self dispose:)
			)
		)
	)
)

(instance sFTree9Choice1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 12 0 52 1 5 self 202)
			)
			(1
				(proc201_7 self)
				(proc201_27 1)
				(ego setLoop: 0 posn: 134 95 setCel: 0 show:)
			)
			(2
				(messager say: 12 0 52 6 self 202)
			)
			(3
				(self setScript: (ScriptID 201 8) self)
				(ego setLoop: 1 setCel: 0 posn: 178 95)
			)
			(4
				(messager say: 12 0 52 7 8 self 202)
			)
			(5 (self dispose:))
		)
	)
)

(instance sFTree9Choice2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 12 0 53 1 8 self 202)
			)
			(1
				(ego setLoop: 0 posn: 134 95 setCel: 0)
				(= seconds 3)
			)
			(2
				(ego setLoop: 1 setCel: 0 posn: 178 95)
				(= seconds 1)
			)
			(3
				(messager say: 12 0 53 9 10 self 202)
			)
			(4 (self dispose:))
		)
	)
)

(instance sDThrakusChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
				(localproc_00ee 26 0 74)
			)
			(1
				(messager say: 26 0 74 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(proc201_5 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(messager say: 26 0 75 0 self 202)
						)
						(2
							(messager say: 26 0 76 0 self 202)
						)
						(3
							(messager say: 26 0 77 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3
				(proc201_5 1)
				(self dispose:)
			)
		)
	)
)

(instance sFThrakusChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 79)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 79 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 80 0 self 202)
						)
						(2
							(messager say: 12 0 81 0 self 202)
						)
						(3
							(messager say: 12 0 82 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFAfterClorox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 64)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 64 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 65 0 self 202)
						)
						(2
							(messager say: 12 0 66 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sFBeforeGoliath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00a2 12 0 83)
				(= cycles 1)
			)
			(1
				(messager say: 12 0 83 1 self 202)
			)
			(2
				((ScriptID 201 13) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 13) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 13) whichSelect?)
						(1
							(messager say: 12 0 84 0 self 202)
						)
						(2
							(messager say: 12 0 85 0 self 202)
						)
						(3
							(messager say: 12 0 87 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sDAfterClorox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_00ee 26 0 70)
				(= cycles 1)
			)
			(1
				(messager say: 26 0 70 1 self 202)
			)
			(2
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(messager say: 26 0 71 0 self 202)
						)
						(2
							(messager say: 26 0 72 0 self 202)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sAsteroidChoices of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 13 0 20 1 self 201)
			)
			(1
				(= cycles 1)
				(localproc_00ee 22 0 8)
			)
			(2
				(messager say: 22 0 8 1 self 202)
			)
			(3
				((ScriptID 201 12) normal: 1 keepWindow: 0)
				(if ((ScriptID 201 12) whichSelect?)
					(theGame handsOff:)
					(switch ((ScriptID 201 12) whichSelect?)
						(1
							(eureka setScript: 0)
							(eureka warnings: 3 timer: 5)
							(Bclr 42)
							(SolvePuzzle 189 35)
						)
						(2)
						(3)
						(else  (= cycles 1))
					)
				else
					(self dispose:)
				)
				(= cycles 1)
			)
			(4
				(theGame handsOff:)
				(self dispose:)
			)
		)
	)
)

(instance sDrooleComments of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 0 posn: 134 95 setCel: 0 show:)
				(proc201_7 self)
			)
			(1
				(messager say: 12 0 10 4 self 202)
			)
			(2
				(ego setLoop: 1 setCel: 0 posn: 178 95)
				(= cycles 2)
			)
			(3
				(messager say: 12 0 10 5 self 202)
			)
			(4
				(self setScript: (ScriptID 201 8) self)
			)
			(5
				(= local0 0)
				(self dispose:)
			)
		)
	)
)
