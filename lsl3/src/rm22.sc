;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use System)

(public
	rm22 0
)

(instance rm22 of Locale
	(method (handleEvent event &tmp i [temp1 3] [buf1 30] [buf2 30] [buf3 30])
		(if (or (not (Btst fQAEnabled)) (event claimed?))
			(return)
		)
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(`@c
						(Show CMAP)
						(Animate (cast elements?) FALSE)
						(while (== nullEvt ((= event (Event new:)) type?))
							(event dispose:)
						)
						(event dispose:)
						(Show VMAP)
						(return)
					)
					(`@d
						(SetDebug)
					)
					(`@i
						(User canInput: TRUE)
					)
					(`@m
						(theGame showMem:)
					)
					(`@n
						(= buf2 0)
						(= buf3 0)
						(= i 1)
						(++ noteNum)
						(while (GetInput @buf3 40 {Press "Enter" or "ESC" when done.})
							(Format @buf1 22 0 (theGame name?) @noteFileNameBuf)
							(Format @buf2 22 1
								curRoomNum
								version
								@QANoteBuf
								@noteFileNameBuf
								noteNum
								i
								(ego view?) (ego x?) (ego y?)
							)
							(File
								name: @buf1
								write: @buf2 @buf3 {\0D\n}
								close:
							)
							(= buf3 0)
							(++ i)
						)
					)
					(`@p
						(Show PMAP)
					)
					(`@r
						(Print (Format @buf1 22 2 curRoomNum))
					)
					(`@v
						(Show VMAP)
					)
					(`@x
						(= quit TRUE)
					)
					(`@z
						(= quit TRUE)
					)
					(`^d
						(= i (GetNumber {Teleport to}))
						(if (Load SCRIPT i)
							(NormalEgo)
							(curRoom newRoom: i)
						else
							(Print 22 3)
							(SetDebug)
						)
					)
					(`^e
						(Print
							(Format
								@buf1
								{view %d loop %d cel %d posn %d %d pri %d OnControl $%x Origin on $%x}
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
								(ego onControl:)
								(ego onControl: origin)
							)
							#icon (ego view?) (ego loop?) (ego cel?)
						)
					)
					(8
						(theGame showMem:)
					)
				)
			)
			(saidEvent
				(if (Said 'tp')
					(= i (GetNumber {Teleport to}))
					(if (Load SCRIPT i)
						(NormalEgo)
						(curRoom newRoom: i)
					else
						(Print 22 3)
						(SetDebug)
					)
				)
			)
		)
		(if (not (event claimed?))
			(super handleEvent: event)
		)
	)
)
